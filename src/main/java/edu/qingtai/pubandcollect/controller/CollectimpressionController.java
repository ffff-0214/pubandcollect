package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.CollectimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collectImpression")
public class CollectimpressionController {
    private CollectimpressionService collectimpressionService;

    @Autowired
    public CollectimpressionController(final CollectimpressionService collectimpressionService){
        this.collectimpressionService = collectimpressionService;
    }

    @PostMapping
    public void saveImpressionCollect(@RequestParam("uuid") String uuid,
                                      @RequestParam("rd3session") String rd3session){
        collectimpressionService.collectImpression(uuid, rd3session);
    }
}
