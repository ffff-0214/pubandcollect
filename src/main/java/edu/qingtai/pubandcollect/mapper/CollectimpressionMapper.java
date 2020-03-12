package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Collectimpression;
import edu.qingtai.pubandcollect.domain.CollectimpressionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectimpressionMapper {
    long countByExample(CollectimpressionExample example);

    int deleteByExample(CollectimpressionExample example);

    int deleteByPrimaryKey(@Param("uuid") String uuid, @Param("openid") String openid);

    int insert(Collectimpression record);

    int insertSelective(Collectimpression record);

    List<Collectimpression> selectByExample(CollectimpressionExample example);

    int updateByExampleSelective(@Param("record") Collectimpression record, @Param("example") CollectimpressionExample example);

    int updateByExample(@Param("record") Collectimpression record, @Param("example") CollectimpressionExample example);
}