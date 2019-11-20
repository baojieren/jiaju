package ink.baojie.jiaju.service.impl;

import ink.baojie.jiaju.base.exception.CustomError;
import ink.baojie.jiaju.data.bo.LoginBO;
import ink.baojie.jiaju.data.dao.UserDao;
import ink.baojie.jiaju.data.po.UserPo;
import ink.baojie.jiaju.service.UserService;
import ink.baojie.jiaju.service.dto.BaseOutDTO;
import ink.baojie.jiaju.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
            return outDTO.fail(CustomError.ERR_USER_NOT_EXIST);
        }

        if (!oneByPhone.getPassword().equals(userPo.getPassword())) {
            return outDTO.fail(CustomError.ERR_LOGIN_WRONG_ACC_OR_PW);
        }

        LoginBO bo = new LoginBO();
        BeanUtils.copyProperties(oneByPhone, bo);
        bo.setToken(JwtUtils.sign(oneByPhone.getId().toString(), null));
        outDTO.setData(bo);
        return outDTO;
    }

    @Override
    public BaseOutDTO miniLogin(UserPo userPo) {
        BaseOutDTO outDTO = new BaseOutDTO();
        UserPo oneByOpenId = userDao.selectOneByOpenId(userPo.getOpenId());
        if (ObjectUtils.isEmpty(oneByOpenId)) {
            return outDTO.fail(CustomError.ERR_USER_NOT_EXIST);
        }

        LoginBO bo = new LoginBO();
        BeanUtils.copyProperties(oneByOpenId, bo);
        bo.setToken(JwtUtils.sign(oneByOpenId.getId().toString(), null));
        outDTO.setData(bo);
        return outDTO;
    }
}
