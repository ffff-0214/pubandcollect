package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;
import edu.qingtai.pubandcollect.service.CollectinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collectInterview")
public class CollectinterviewController {
    private CollectinterviewService collectinterviewService;

    @Autowired
    public CollectinterviewController(final CollectinterviewService collectinterviewService){
        this.collectinterviewService = collectinterviewService;
    }

    @PostMapping
    public void saveImpressionCollect(@RequestBody Map<String,String> info){
        collectinterviewService.saveCollectInterview(info.get("uuid"), info.get("rd3session"));
    }

    @GetMapping
    public List<PubinterviewVo> queryInterviewByOpenid(@RequestParam("rd3session") String rd3session){
        return collectinterviewService.queryInterviewFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteInterviewCollect(@RequestBody Map<String,String> info){
        collectinterviewService.deleteCollectInterview(info.get("uuid"), info.get("rd3session"));
    }
}
