package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Collectinterview;
import edu.qingtai.pubandcollect.domain.CollectinterviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectinterviewMapper {
    long countByExample(CollectinterviewExample example);

    int deleteByExample(CollectinterviewExample example);

    int deleteByPrimaryKey(@Param("uuid") String uuid, @Param("openid") String openid);

    int insert(Collectinterview record);

    int insertSelective(Collectinterview record);

    List<Collectinterview> selectByExample(CollectinterviewExample example);

    int updateByExampleSelective(@Param("record") Collectinterview record, @Param("example") CollectinterviewExample example);

    int updateByExample(@Param("record") Collectinterview record, @Param("example") CollectinterviewExample example);
}