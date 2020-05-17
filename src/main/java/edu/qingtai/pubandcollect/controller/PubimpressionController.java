package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionRec;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;
import edu.qingtai.pubandcollect.domain.PubimpressionVoDetail;
import edu.qingtai.pubandcollect.service.PubimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/impression")
public class PubimpressionController {
    private PubimpressionService pubimpressionService;

    @Autowired
    public PubimpressionController(final PubimpressionService pubimpressionService){
        this.pubimpressionService = pubimpressionService;
    }

    @PostMapping
    public void Publish(@RequestBody PubimpressionRec pubimpressionRec){
        pubimpressionService.saveImpression(pubimpressionRec.getPosition(),
                pubimpressionRec.getCompany(),
                pubimpressionRec.getWorkplace(),
                pubimpressionRec.getEducation(),
                pubimpressionRec.getSalary(),
                pubimpressionRec.getLabel(),
                pubimpressionRec.getContent(),
                pubimpressionRec.getRd3session(),
                pubimpressionRec.getUsername(),
                pubimpressionRec.getUserimage());
    }

    @GetMapping(value = "/myself")
    public List<PubimpressionVo> queryMyImpression(@RequestParam("rd3session") String rd3session){
        return pubimpressionService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    public void deleteImpression(@RequestBody Map<String,String> uuid){
        pubimpressionService.deleteImpression(uuid.get("uuid"));
    }

    @GetMapping(value = "/content")
    public PubimpressionVoDetail getImpressionContent(@RequestParam("uuid") String uuid,
                                                      @RequestParam("rd3session") String rd3session){
        return pubimpressionService.queryContent(uuid, rd3session);
    }

    @GetMapping
    public List<PubimpressionVo> queryImpression(@RequestParam("pageIndex") int pageIndex,
                                                 @RequestParam("rd3session") String rd3session){
        return pubimpressionService.queryTrueImpressions(pageIndex, rd3session);
    }

    @GetMapping(value = "/upTruth")
    public void upTruth(@RequestParam("uuid") String uuid){
        pubimpressionService.upTruth(uuid);
    }

    @GetMapping(value = "/downTruth")
    public void downTruth(@RequestParam("uuid") String uuid){
        pubimpressionService.downTruth(uuid);
    }
}
