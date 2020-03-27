package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.service.CollectinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        collectinferService.saveCollectInfer(uuid, rd3session);
    }

    @GetMapping
    public List<Pubinfer> queryInferByOpenid(@RequestParam("rd3session") String rd3session){
        return collectinferService.queryInferFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteInferCollect(@RequestParam("uuid") String uuid,
                                   @RequestParam("rd3session") String rd3session){
        collectinferService.deleteCollectInfer(uuid, rd3session);
    }
}
