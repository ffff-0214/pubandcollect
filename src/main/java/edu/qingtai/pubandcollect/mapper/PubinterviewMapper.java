package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PubinterviewMapper {
    long countByExample(PubinterviewExample example);

    int deleteByExample(PubinterviewExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Pubinterview record);

    int insertSelective(Pubinterview record);

    List<Pubinterview> selectByExampleWithBLOBs(PubinterviewExample example);

    List<Pubinterview> selectByExample(PubinterviewExample example);

    Pubinterview selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Pubinterview record, @Param("example") PubinterviewExample example);

    int updateByExampleWithBLOBs(@Param("record") Pubinterview record, @Param("example") PubinterviewExample example);

    int updateByExample(@Param("record") Pubinterview record, @Param("example") PubinterviewExample example);

    int updateByPrimaryKeySelective(Pubinterview record);

    int updateByPrimaryKeyWithBLOBs(Pubinterview record);

    int updateByPrimaryKey(Pubinterview record);

    List<String> selectUuidByPageIndex(@Param("startIndex") int startIndex, @Param("pageSize")int pageSize);

    List<Pubinterview> selectInterviewByUuidList(@Param("uuidList") List<String> uuidList);

    List<Pubinterview> selectMyPublish(@Param("openid") String openid);
}