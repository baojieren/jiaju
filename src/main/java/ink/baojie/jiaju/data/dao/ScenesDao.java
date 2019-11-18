package ink.baojie.jiaju.data.dao;

import ink.baojie.jiaju.data.po.ScenesPo;

public interface ScenesDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ScenesPo record);

    int insertSelective(ScenesPo record);

    ScenesPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScenesPo record);

    int updateByPrimaryKey(ScenesPo record);
}