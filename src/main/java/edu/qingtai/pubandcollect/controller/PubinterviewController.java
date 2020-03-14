package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.PubinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/interview")
public class PubinterviewController {
    private PubinterviewService pubinterviewService;

    @Autowired
    public PubinterviewController(final PubinterviewService pubinterviewService){
        this.pubinterviewService = pubinterviewService;
    }

    @PostMapping
    public void Publish(@RequestParam("title") String title,
                        @RequestParam("rd3session") String rd3session,
                        @RequestParam("content") String content,
                        @RequestParam("fileList") List<MultipartFile> fileList){
        pubinterviewService.saveInterview(title,rd3session,content,fileList);
    }
}
