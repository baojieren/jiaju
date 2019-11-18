package ink.baojie.jiaju.service.impl;

import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.data.dao.UserDao;
import ink.baojie.jiaju.data.po.UserPo;
import ink.baojie.jiaju.service.UserService;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public BaseOutDTO login(UserPo userPo) {
        BaseOutDTO outDTO = new BaseOutDTO();
        UserPo oneByPhone = userDao.selectOneByPhone(userPo.getPhone());
        if (ObjectUtils.isEmpty(oneByPhone)) {
            return outDTO.fail(CustomError.ERR_USER_ALREADY_EXIST);
        }

        if (!oneByPhone.getPassword().equals(userPo.getPassword())) {
            return outDTO.fail(CustomError.ERR_LOGIN_WRONG_ACC_OR_PW);
        }

        String token = JwtUtils.sign(oneByPhone.getId().toString(), null);
        outDTO.setData(token);
        return outDTO;
    }
}
