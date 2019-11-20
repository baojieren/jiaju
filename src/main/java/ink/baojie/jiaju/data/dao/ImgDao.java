package ink.baojie.jiaju.data.dao;
import org.apache.ibatis.annotations.Param;

import ink.baojie.jiaju.data.po.ImgPo;

public interface ImgDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ImgPo record);

    int insertSelective(ImgPo record);

    ImgPo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImgPo record);

    int updateByPrimaryKey(ImgPo record);

    ImgPo selectOneByUrl(@Param("url")String url);

}
