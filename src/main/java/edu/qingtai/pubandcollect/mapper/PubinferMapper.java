package edu.qingtai.pubandcollect.mapper;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PubinferMapper {
    long countByExample(PubinferExample example);

    int deleteByExample(PubinferExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Pubinfer record);

    int insertSelective(Pubinfer record);

    List<Pubinfer> selectByExampleWithBLOBs(PubinferExample example);

    List<Pubinfer> selectByExample(PubinferExample example);

    Pubinfer selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Pubinfer record, @Param("example") PubinferExample example);

    int updateByExampleWithBLOBs(@Param("record") Pubinfer record, @Param("example") PubinferExample example);

    int updateByExample(@Param("record") Pubinfer record, @Param("example") PubinferExample example);

    int updateByPrimaryKeySelective(Pubinfer record);

    int updateByPrimaryKeyWithBLOBs(Pubinfer record);

    int updateByPrimaryKey(Pubinfer record);
}