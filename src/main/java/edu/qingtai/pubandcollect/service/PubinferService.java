package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinferService {
    void saveInfer(Pubinfer pubinfer, List<MultipartFile> fileList);

}
