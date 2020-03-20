package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Infer;
import edu.qingtai.pubandcollect.service.PubinferService;
import edu.qingtai.pubandcollect.util.QuireDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/infer")
public class PubinferController {
    private PubinferService pubinferService;
    private EventDispatcher eventDispatcher;

    @Autowired
    public PubinferController(final PubinferService pubinferService,
                              final EventDispatcher eventDispatcher){
        this.pubinferService = pubinferService;
        this.eventDispatcher = eventDispatcher;
    }

    @PostMapping
    public void Publish(@RequestParam("title") String title,
                        @RequestParam("label") String label,
                        @RequestParam("rd3session") String rd3session,
                        @RequestParam("content") String content,
                        @RequestParam("fileList") List<MultipartFile> fileList){
        pubinferService.saveInfer(title, label, rd3session, content, fileList);
    }

    @GetMapping
    public void test(){
        eventDispatcher.sendInfer(new Infer("1", "11", "111", QuireDate.currentDate(), "1111", 2, "11111"));
    }
}
