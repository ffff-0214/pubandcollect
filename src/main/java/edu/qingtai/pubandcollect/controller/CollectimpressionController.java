package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;
import edu.qingtai.pubandcollect.service.CollectimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        collectimpressionService.saveCollectImpression(uuid, rd3session);
    }

    @GetMapping
    public List<PubimpressionVo> queryImpressionByOpenid(@RequestParam("rd3session") String rd3session){
        return collectimpressionService.queryImpressionFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteImpressionCollect(@RequestParam("uuid") String uuid,
                                        @RequestParam("rd3session") String rd3session){
        collectimpressionService.deleteCollectImpression(uuid, rd3session);
    }
}
