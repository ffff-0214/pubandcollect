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
                        @RequestParam("openid") String openid,
                        @RequestParam("content") String content,
                        @RequestParam("fileList") List<MultipartFile> fileList){
        Pubinfer pubinfer = new Pubinfer();
        pubinfer.setTitle(title);
        pubinfer.setLabel(label);
        pubinfer.setOpenid(openid);
        pubinfer.setContent(content);
        pubinferService.saveInfer(pubinfer, fileList);
    }
}
