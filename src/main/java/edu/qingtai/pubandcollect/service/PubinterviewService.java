package edu.qingtai.pubandcollect.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PubinterviewService {
    void saveInterview(String title, String rd3session, String content, List<MultipartFile> fileList);
}
