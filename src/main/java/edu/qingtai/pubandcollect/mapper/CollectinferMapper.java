package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Collectinfer;
import edu.qingtai.pubandcollect.domain.CollectinferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectinferMapper {
    long countByExample(CollectinferExample example);

    int deleteByExample(CollectinferExample example);

    int deleteByPrimaryKey(@Param("uuid") String uuid, @Param("openid") String openid);

    int insert(Collectinfer record);

    int insertSelective(Collectinfer record);

    List<Collectinfer> selectByExample(CollectinferExample example);

    int updateByExampleSelective(@Param("record") Collectinfer record, @Param("example") CollectinferExample example);

    int updateByExample(@Param("record") Collectinfer record, @Param("example") CollectinferExample example);
}