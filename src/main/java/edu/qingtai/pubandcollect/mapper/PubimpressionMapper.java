package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PubimpressionMapper {
    long countByExample(PubimpressionExample example);

    int deleteByExample(PubimpressionExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Pubimpression record);

    int insertSelective(Pubimpression record);

    List<Pubimpression> selectByExampleWithBLOBs(PubimpressionExample example);

    List<Pubimpression> selectByExample(PubimpressionExample example);

    Pubimpression selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Pubimpression record, @Param("example") PubimpressionExample example);

    int updateByExampleWithBLOBs(@Param("record") Pubimpression record, @Param("example") PubimpressionExample example);

    int updateByExample(@Param("record") Pubimpression record, @Param("example") PubimpressionExample example);

    int updateByPrimaryKeySelective(Pubimpression record);

    int updateByPrimaryKeyWithBLOBs(Pubimpression record);

    int updateByPrimaryKey(Pubimpression record);

    List<String> selectUuidByPageIndex(@Param("startIndex") int startIndex, @Param("pageSize")int pageSize);

    List<Pubimpression> selectImpressionByUuidList(@Param("uuidList") List<String> uuidList);

    List<Pubimpression> selectMyPublish(@Param("openid") String openid);
}