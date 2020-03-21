package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubimpression;

import java.util.List;

public interface CollectimpressionService {
    void saveCollectImpression(String uuid, String rd3session);
    List<Pubimpression> queryImpressionFromOpenid(String rd3session);
    void deleteCollectImpression(String uuid, String rd3session);
}
