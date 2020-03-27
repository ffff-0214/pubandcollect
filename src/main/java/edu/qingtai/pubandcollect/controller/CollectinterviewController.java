package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.service.CollectinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/collectInterview")
public class CollectinterviewController {
    private CollectinterviewService collectinterviewService;

    @Autowired
    public CollectinterviewController(final CollectinterviewService collectinterviewService){
        this.collectinterviewService = collectinterviewService;
    }

    @PostMapping
    public void saveImpressionCollect(@RequestParam("uuid") String uuid,
                                      @RequestParam("rd3session") String rd3session){
        collectinterviewService.saveCollectInterview(uuid, rd3session);
    }

    @GetMapping
    public List<Pubinterview> queryInterviewByOpenid(@RequestParam("rd3session") String rd3session){
        return collectinterviewService.queryInterviewFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteInterviewCollect(@RequestParam("uuid") String uuid,
                                       @RequestParam("rd3session") String rd3session){
        collectinterviewService.deleteCollectInterview(uuid, rd3session);
    }
}
