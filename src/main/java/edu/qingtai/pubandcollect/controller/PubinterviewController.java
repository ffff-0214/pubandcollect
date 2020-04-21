package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;
import edu.qingtai.pubandcollect.service.PubinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
                        @RequestParam("username") String username,
                        @RequestParam("userimage") String userimage,
                        @RequestParam("images") String images){
        pubinterviewService.saveInterview(title, rd3session, content, username, userimage, images);
    }

    @GetMapping(value = "/myself")
    public List<Pubinterview> queryMyPublish(@RequestParam("rd3session") String rd3session){
        return pubinterviewService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    public void deleteInterview(@RequestParam("uuid") String uuid){
        pubinterviewService.deleteInterview(uuid);
    }

    @GetMapping
    public List<PubinterviewVo> queryInterview(@RequestParam("pageIndex") int pageIndex,
                                               @RequestParam("rd3session") String rd3session){
        return pubinterviewService.queryTrueInterviews(pageIndex, rd3session);
    }
}
