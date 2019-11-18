package ink.baojie.jiaju.data.dao;
import org.apache.ibatis.annotations.Param;

import ink.baojie.jiaju.data.po.UserPo;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);

    UserPo selectOneByPhone(@Param("phone")String phone);

}
