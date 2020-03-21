package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinterviewService {
    void saveInterview(String title, String rd3session, String content, List<MultipartFile> fileList);

    List<Pubinterview> queryMyPublish(String rd3session);

    void deleteInterview(String uuid);
}
