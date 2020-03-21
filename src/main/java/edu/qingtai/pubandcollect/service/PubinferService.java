package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinferService {
    void saveInfer(String title, String label, String rd3session, String content, List<MultipartFile> fileList);

    List<Pubinfer> queryMyPublish(String rd3session);

    void deleteInfer(String uuid);
}
