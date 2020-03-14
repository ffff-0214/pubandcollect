package edu.qingtai.pubandcollect.service;

public interface PubimpressionService {
    void saveImpression(String position, String company, String workPlace, String education, String salary,
                        String label, String content, String rd3session);
}
