package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;

import java.util.List;

public interface CollectinterviewService {
    void saveCollectInterview(String uuid, String rd3session);
    List<Pubinterview> queryInterviewFromOpenid(String rd3session);
    void deleteCollectInterview(String uuid, String rd3session);
}
