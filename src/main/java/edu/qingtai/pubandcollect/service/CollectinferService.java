package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;

import java.util.List;

public interface CollectinferService {
    void saveCollectInfer(String uuid, String rd3session);
    List<Pubinfer> queryInferFromOpenid(String rd3session);
    void deleteCollectInfer(String uuid, String rd3session);
}
