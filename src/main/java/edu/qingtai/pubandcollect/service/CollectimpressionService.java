package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;

import java.util.List;

public interface CollectimpressionService {
    void saveCollectImpression(String uuid, String rd3session);
    List<PubimpressionVo> queryImpressionFromOpenid(String rd3session);
    void deleteCollectImpression(String uuid, String rd3session);
}
