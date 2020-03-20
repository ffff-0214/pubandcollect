package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.CollectinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/collectInfer")
public class CollectinferController {
    private CollectinferService collectinferService;

    @Autowired
    public CollectinferController(final CollectinferService collectinferService){
        this.collectinferService = collectinferService;
    }

    @PostMapping
    public void saveImpressionCollect(@RequestParam("uuid") String uuid,
                                      @RequestParam("rd3session") String rd3session){
        collectinferService.collectInfer(uuid, rd3session);
    }
}
