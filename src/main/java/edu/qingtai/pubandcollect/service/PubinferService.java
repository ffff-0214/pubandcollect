package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinferService {
    void saveInfer(String title, String label, String rd3session, String content, String username,
                   String userimage, String images);

    List<PubinferVo> queryMyPublish(String rd3session);

    void deleteInfer(String uuid);

    List<Pubinfer> queryInfers(int pageIndex);

    List<PubinferVo> queryTrueInfers(int pageIndex, String rd3session);
}
