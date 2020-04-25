package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;

import java.util.List;

public interface CollectinterviewService {
    void saveCollectInterview(String uuid, String rd3session);
    List<PubinterviewVo> queryInterviewFromOpenid(String rd3session);
    void deleteCollectInterview(String uuid, String rd3session);
}
