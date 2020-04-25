package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferVo;

import java.util.List;

public interface CollectinferService {
    void saveCollectInfer(String uuid, String rd3session);
    List<PubinferVo> queryInferFromOpenid(String rd3session);
    void deleteCollectInfer(String uuid, String rd3session);
}
