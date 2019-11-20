package ink.baojie.jiaju.web;

import ink.baojie.jiaju.service.ScenesService;
import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.service.dto.SaveScenesInDTO;
import ink.baojie.jiaju.service.dto.SelectScenesListInDTO;
import ink.baojie.jiaju.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/scenes")
public class ScenesController {
    @Resource
    ScenesService scenesService;

    /**
     * 添加系列
     */
    @PostMapping("{type}")
    public BaseOutDTO saveAndUpdateScenes(@RequestBody SaveScenesInDTO inDTO, @PathVariable String type) {
        if ("update".equals(type)) {
            CheckUtil.checkEmpty("id", inDTO.getImgUrlList());
        }
        CheckUtil.checkEmpty("系列名称", inDTO.getTitle());
        CheckUtil.checkMin("图片数量", inDTO.getImgUrlList().size(), 1);
        return scenesService.saveAndUpdateScenes(inDTO, type);
    }

    /**
     * 分页获取系列
     */
    @PostMapping("list")
    public BaseListOutDTO selectAllList(@RequestBody SelectScenesListInDTO inDTO) {
        CheckUtil.checkEmpty("页码", inDTO.getPageIndex());
        CheckUtil.checkEmpty("每页数", inDTO.getPageSize());
        return scenesService.selectPageList(inDTO);
    }

    /**
     * 根据id查询详情
     */
    @GetMapping("one")
    public BaseOutDTO selectOne(Integer id) {
        CheckUtil.checkEmpty("id", id);
        return scenesService.selectOne(id);
    }

    /**
     * 上下架
     */
    @GetMapping("valid")
    public BaseOutDTO changeValid(Integer id, Integer valid) {
        CheckUtil.checkEmpty("id", id);
        CheckUtil.checkEmpty("状态", valid);
        return scenesService.changeValid(id, valid);
    }


}
