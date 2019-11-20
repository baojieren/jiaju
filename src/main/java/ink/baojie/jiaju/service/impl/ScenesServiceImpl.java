package ink.baojie.jiaju.service.impl;

import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.data.bo.QueryScenesBO;
import ink.baojie.jiaju.data.dao.ImgDao;
import ink.baojie.jiaju.data.dao.ScenesDao;
import ink.baojie.jiaju.data.po.ImgPo;
import ink.baojie.jiaju.data.po.ScenesPo;
import ink.baojie.jiaju.service.ScenesService;
import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.service.dto.SaveScenesInDTO;
import ink.baojie.jiaju.service.dto.SelectScenesListInDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
@Slf4j
public class ScenesServiceImpl implements ScenesService {
    @Resource
    ScenesDao scenesDao;
    @Resource
    ImgDao imgDao;

    @Override
    @Transactional
    public BaseOutDTO saveAndUpdateScenes(SaveScenesInDTO inDTO, String type) {
        BaseOutDTO outDTO = new BaseOutDTO();
        // 保存图片
        StringJoiner imgIds = new StringJoiner(",");
        for (String imgUrl : inDTO.getImgUrlList()) {
            // 先看看图片是否存在
            ImgPo imgPo = imgDao.selectOneByUrl(imgUrl);
            if (!ObjectUtils.isEmpty(imgPo)) {
                imgIds.add(String.valueOf(imgPo.getId()));
                continue;
            }
            ImgPo saveImg = new ImgPo();
            saveImg.setUrl(imgUrl);
            imgDao.insertSelective(saveImg);
            imgIds.add(String.valueOf(saveImg.getId()));
        }

        // 再保存系列信息
        ScenesPo scenesPo = new ScenesPo();
        BeanUtils.copyProperties(inDTO, scenesPo);
        scenesPo.setImg(imgIds.toString());
        if ("save".equals(type)) {
            scenesDao.insertSelective(scenesPo);
        } else {
            scenesDao.updateByPrimaryKeySelective(scenesPo);
        }
        return outDTO;
    }

    @Override
    public BaseListOutDTO selectPageList(SelectScenesListInDTO inDTO) {
        BaseListOutDTO outDTO = new BaseListOutDTO();
        int count = scenesDao.countByCond(inDTO);
        if (count > 0) {
            List<QueryScenesBO> boList = new ArrayList<>();
            outDTO.setData(boList);
            List<ScenesPo> scenesPos = scenesDao.selectPageListByCond(inDTO);
            for (ScenesPo scenesPo : scenesPos) {
                QueryScenesBO bo = new QueryScenesBO();
                boList.add(bo);
                BeanUtils.copyProperties(scenesPo, bo);
                if (!StringUtils.isEmpty(scenesPo.getImg())) {
                    String[] imgIds = scenesPo.getImg().split(",");

                    List<String> imgUrlList = new ArrayList<>();
                    bo.setImg(imgUrlList);
                    for (String imgId : imgIds) {
                        ImgPo imgPo = imgDao.selectByPrimaryKey(Integer.parseInt(imgId));
                        imgUrlList.add(imgPo.getUrl());
                    }
                }
            }
        }
        outDTO.setTotal(count);
        return outDTO;
    }

    @Override
    public BaseOutDTO selectOne(Integer id) {
        BaseOutDTO outDTO = new BaseOutDTO();
        ScenesPo scenesPo = scenesDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(scenesPo)) {
            return outDTO.fail(new CustomError().nextError("系列不存在"));
        }

        QueryScenesBO bo = new QueryScenesBO();
        BeanUtils.copyProperties(scenesPo, bo);

        // 查询图片
        List<String> imgUrlList = new ArrayList<>();
        for (String imgId : scenesPo.getImg().split(",")) {
            ImgPo imgPo = imgDao.selectByPrimaryKey(Integer.parseInt(imgId));
            imgUrlList.add(ObjectUtils.isEmpty(imgPo) ? "" : imgPo.getUrl());
        }
        bo.setImg(imgUrlList);
        outDTO.setData(bo);
        return outDTO;
    }

    @Override
    public BaseOutDTO changeValid(int id, int valid) {
        ScenesPo record = new ScenesPo();
        record.setId(id);
        record.setValid(valid);
        scenesDao.updateByPrimaryKeySelective(record);
        return new BaseOutDTO();
    }
}
