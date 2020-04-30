package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferVo;
import edu.qingtai.pubandcollect.service.CollectinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collectInfer")
public class CollectinferController {
    private CollectinferService collectinferService;

    @Autowired
    public CollectinferController(final CollectinferService collectinferService){
        this.collectinferService = collectinferService;
    }

    @PostMapping
    public void saveImpressionCollect(@RequestBody Map<String,String> info){
        collectinferService.saveCollectInfer(info.get("uuid"), info.get("rd3session"));
    }

    @GetMapping
    public List<PubinferVo> queryInferByOpenid(@RequestParam("rd3session") String rd3session){
        return collectinferService.queryInferFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteInferCollect(@RequestBody Map<String,String> info){
        collectinferService.deleteCollectInfer(info.get("uuid"), info.get("rd3session"));
    }
}
