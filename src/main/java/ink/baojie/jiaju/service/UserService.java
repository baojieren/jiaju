package ink.baojie.jiaju.service;

import ink.baojie.jiaju.data.po.UserPo;
import ink.baojie.jiaju.service.dto.BaseOutDTO;

public interface UserService {

    BaseOutDTO login(UserPo userPo);

    BaseOutDTO miniLogin(UserPo userPo);

}
