package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;
import edu.qingtai.pubandcollect.domain.PubinterviewVoDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinterviewService {
    void saveInterview(String title, String rd3session, String content,
                       String username, String userimage, String images);

    List<PubinterviewVo> queryMyPublish(String rd3session);

    void deleteInterview(String uuid);

    List<Pubinterview> queryInterviews(int pageIndex);

    List<PubinterviewVo> queryTrueInterviews(int pageIndex, String rd3session);

    PubinterviewVoDetail queryContent(String uuid, String rd3session);
}
