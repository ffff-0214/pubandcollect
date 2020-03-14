package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.service.PubinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/infer")
public class PubinferController {
    private PubinferService pubinferService;

    @Autowired
    public PubinferController(final PubinferService pubinferService){
        this.pubinferService = pubinferService;
    }

    @PostMapping
    public void Publish(@RequestParam("title") String title,
                        @RequestParam("label") String label,
                        @RequestParam("rd3session") String rd3session,
                        @RequestParam("content") String content,
                        @RequestParam("fileList") List<MultipartFile> fileList){
        pubinferService.saveInfer(title, label, rd3session, content, fileList);
    }
}
