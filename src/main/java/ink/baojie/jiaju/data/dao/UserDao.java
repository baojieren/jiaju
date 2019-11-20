package ink.baojie.jiaju.data.dao;
import java.util.List;

import ink.baojie.jiaju.data.po.UserPo;import org.apache.ibatis.annotations.Param;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);

    UserPo selectOneByPhone(@Param("phone") String phone);

    UserPo selectOneByOpenId(@Param("openId")String openId);


}
