package ink.baojie.jiaju.service.impl;

import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.data.bo.QueryScenesBO;
import ink.baojie.jiaju.data.dao.ImgDao;
import ink.baojie.jiaju.data.dao.ScenesDao;
import ink.baojie.jiaju.data.po.ImgPo;
import ink.baojie.jiaju.data.po.ScenesPo;
import ink.baojie.jiaju.service.MiniScenesService;
import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MiniScenesServiceImpl implements MiniScenesService {

    @Resource
    ScenesDao scenesDao;
    @Resource
    ImgDao imgDao;

    @Override
    public BaseListOutDTO selectScenesAll() {
        BaseListOutDTO outDTO = new BaseListOutDTO();
        List<ScenesPo> scenesPoList = scenesDao.selectScenesListForMini();

        List<QueryScenesBO> scenesBOList = new ArrayList<>();

        if (!ObjectUtils.isEmpty(scenesPoList)) {
            for (ScenesPo scenesPo : scenesPoList) {
                QueryScenesBO bo = new QueryScenesBO();
                BeanUtils.copyProperties(scenesPo, bo);
                scenesBOList.add(bo);
                List<String> imgUrls = new ArrayList<>();
                bo.setImg(imgUrls);
                String[] imgIds = scenesPo.getImg().split(",");
                for (String imgId : imgIds) {
                    ImgPo imgPo = imgDao.selectByPrimaryKey(Integer.parseInt(imgId));
                    imgUrls.add(imgPo.getUrl());
                }
            }
        }
        return outDTO.setData(scenesBOList);
    }

    @Override
    public BaseOutDTO selectImgsByScenesId(int id) {
        BaseOutDTO outDTO = new BaseOutDTO();
        ScenesPo scenesPo = scenesDao.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(scenesPo)) {
            return outDTO.fail(new CustomError().nextError("系列不存在"));
        }

        List<String> imgs = new ArrayList<>();
        String[] imgIds = scenesPo.getImg().split(",");
        for (String imgId : imgIds) {
            ImgPo imgPo = imgDao.selectByPrimaryKey(Integer.parseInt(imgId));
            imgs.add(imgPo.getUrl());
        }
        outDTO.setData(imgs);
        return outDTO;
    }
}
