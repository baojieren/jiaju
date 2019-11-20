package ink.baojie.jiaju.web;

import ink.baojie.jiaju.data.po.UserPo;
import ink.baojie.jiaju.service.UserService;
import ink.baojie.jiaju.service.dto.BaseListOutDTO;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;

    /**
     * mng登录
     */
    @PostMapping("login")
    public BaseOutDTO login(@RequestBody UserPo userPo) {
        CheckUtil.checkEmpty("手机号", userPo.getPhone());
        CheckUtil.checkEmpty("密码", userPo.getPassword());
        return userService.login(userPo);
    }

    /**
     * mini登录
     */
    @PostMapping("mini/login")
    public BaseOutDTO miniLogin(@RequestBody UserPo userPo) {
        CheckUtil.checkEmpty("openId", userPo.getOpenId());
        return userService.login(userPo);
    }
}
