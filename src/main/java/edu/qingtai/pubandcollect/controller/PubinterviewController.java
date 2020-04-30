package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewRec;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;
import edu.qingtai.pubandcollect.service.PubinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/interview")
public class PubinterviewController {
    private PubinterviewService pubinterviewService;

    @Autowired
    public PubinterviewController(final PubinterviewService pubinterviewService){
        this.pubinterviewService = pubinterviewService;
    }

    @PostMapping
    public void Publish(@RequestBody PubinterviewRec pubinterviewRec){
        pubinterviewService.saveInterview(pubinterviewRec.getTitle(),
                pubinterviewRec.getRd3session(),
                pubinterviewRec.getContent(),
                pubinterviewRec.getUsername(),
                pubinterviewRec.getUserimage(),
                pubinterviewRec.getImages());
    }

    @GetMapping(value = "/myself")
    public List<PubinterviewVo> queryMyPublish(@RequestParam("rd3session") String rd3session){
        return pubinterviewService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    public void deleteInterview(@RequestBody Map<String,String> uuid){
        pubinterviewService.deleteInterview(uuid.get("uuid"));
    }

    @GetMapping
    public List<PubinterviewVo> queryInterview(@RequestParam("pageIndex") int pageIndex,
                                               @RequestParam("rd3session") String rd3session){
        return pubinterviewService.queryTrueInterviews(pageIndex, rd3session);
    }
}
