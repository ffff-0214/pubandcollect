package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.PubimpressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
                        @RequestParam("rd3session") String rd3session){
        pubimpressionService.saveImpression(position, company, workPlace, education, salary, label, content, rd3session);
    }

}
