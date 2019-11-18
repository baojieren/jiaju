package ink.baojie.jiaju.web;

import com.qiniu.util.Auth;
import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.base.exception.CustomException;
import ink.baojie.jiaju.config.Settings;
import ink.baojie.jiaju.data.bo.QiNiuTokenBO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/img")
public class ImgController {
    @Resource
    private Settings settings;

    /**
     * 获取上传token
     */
    @GetMapping("token")
    public BaseOutDTO getToken(String fileName) {
        BaseOutDTO outDTO = new BaseOutDTO();
        Auth auth = Auth.create(settings.getQnAccesskey(), settings.getQnSecretkey());
        String token = auth.uploadToken(settings.getQnBucket(), getFileExtra(fileName));
        QiNiuTokenBO bo = new QiNiuTokenBO(settings.getQnDomain(), token, fileName);
        outDTO.setData(bo);
        return outDTO;
    }

    private String getFileExtra(String fileName) throws CustomException {
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            throw new CustomException(new CustomError(415, "文件格式有错误"));
        }
        return fileName.substring(index);
    }
}
