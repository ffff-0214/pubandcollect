package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubimpression;

import java.util.List;

public interface PubimpressionService {
    void saveImpression(String position, String company, String workPlace, String education, String salary,
                        String label, String content, String rd3session, String username, String userimage);

    List<Pubimpression> queryMyPublish(String rd3session);

    void deleteImpression(String uuid);

    List<Pubimpression> queryImpressions(int PageIndex);
}
