package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;
import edu.qingtai.pubandcollect.service.CollectimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collectImpression")
public class CollectimpressionController {
    private CollectimpressionService collectimpressionService;

    @Autowired
    public CollectimpressionController(final CollectimpressionService collectimpressionService){
        this.collectimpressionService = collectimpressionService;
    }

    @PostMapping
//    @RequestParam("uuid") String uuid,
//    @RequestParam("rd3session") String rd3session
    public void saveImpressionCollect(@RequestBody Map<String,String> info){
        collectimpressionService.saveCollectImpression(info.get("uuid"), info.get("rd3session"));
    }

    @GetMapping
    public List<PubimpressionVo> queryImpressionByOpenid(@RequestParam("rd3session") String rd3session){
        return collectimpressionService.queryImpressionFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteImpressionCollect(@RequestBody Map<String,String> info){
        collectimpressionService.deleteCollectImpression(info.get("uuid"), info.get("rd3session"));
    }
}
