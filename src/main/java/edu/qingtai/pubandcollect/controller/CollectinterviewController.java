package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.CollectinterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        collectinterviewService.collectInterview(uuid, rd3session);
    }
}
