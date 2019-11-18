package ink.baojie.jiaju.data.dao;

import ink.baojie.jiaju.data.po.ImgPo;

public interface ImgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgPo record);

    int insertSelective(ImgPo record);

    ImgPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgPo record);

    int updateByPrimaryKey(ImgPo record);
}