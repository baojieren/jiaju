package ink.baojie.jiaju.web;

import ink.baojie.jiaju.service.MiniScenesService;
import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/mini")
public class MiniScenesController {

    @Resource
    MiniScenesService miniScenesService;

    @GetMapping("scenesList")
    public BaseListOutDTO selectScenesAll() {
        return miniScenesService.selectScenesAll();
    }

    /**
     * 根据系列id查询所有图片
     */
    @GetMapping("scenes/imgs")
    public BaseOutDTO selectImgsByScenesId(Integer id) {
        CheckUtil.checkEmpty("系列id", id);
        return miniScenesService.selectImgsByScenesId(id);
    }
}
