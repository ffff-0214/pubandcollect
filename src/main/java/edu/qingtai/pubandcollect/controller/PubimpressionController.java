package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.service.PubimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/impression")
public class PubimpressionController {
    private PubimpressionService pubimpressionService;

    @Autowired
    public PubimpressionController(final PubimpressionService pubimpressionService){
        this.pubimpressionService = pubimpressionService;
    }

    @PostMapping
    public void Publish(@RequestParam("position") String position,
                        @RequestParam("company") String company,
                        @RequestParam("workplace") String workPlace,
                        @RequestParam("education") String education,
                        @RequestParam("salary") String salary,
                        @RequestParam("label") String label,
                        @RequestParam("content") String content,
                        @RequestParam("rd3session") String rd3session,
                        @RequestParam("username") String username,
                        @RequestParam("userimage") String userimage){
        pubimpressionService.saveImpression(position, company, workPlace, education, salary, label, content,
                rd3session, username, userimage);
    }

    @GetMapping(value = "/myself")
    public List<Pubimpression> queryMyImpression(@RequestParam("rd3session") String rd3session){
        return pubimpressionService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    public void deleteImpression(@RequestParam("uuid") String uuid){
        pubimpressionService.deleteImpression(uuid);
    }

    @GetMapping
    public List<Pubimpression> queryImpression(@RequestParam("pageIndex") int pageIndex){
        return pubimpressionService.queryImpressions(pageIndex);
    }
}
