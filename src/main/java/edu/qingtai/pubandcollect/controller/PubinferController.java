package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferVo;
import edu.qingtai.pubandcollect.service.PubinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/infer")
public class PubinferController {
    private PubinferService pubinferService;

    @Autowired
    public PubinferController(final PubinferService pubinferService){
        this.pubinferService = pubinferService;
    }


//@RequestBody PubinferRec pubinferRec
    @PostMapping
    public void Publish(@RequestParam("title") String title,
                        @RequestParam("label") String label,
                        @RequestParam("rd3session") String rd3session,
                        @RequestParam("content") String content,
                        @RequestParam("username") String username,
                        @RequestParam("userimage") String userimage,
                        @RequestParam("images") String images){
        pubinferService.saveInfer(title, label, rd3session, content, username,
                userimage, images);
    }

    @GetMapping(value = "/myself")
    public List<Pubinfer> queryMyPublish(@RequestParam("rd3session") String rd3session){
        return pubinferService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    public void deleteInfer(@RequestParam("uuid") String uuid){
        pubinferService.deleteInfer(uuid);
    }

    @GetMapping
    public List<PubinferVo> queryInfer(@RequestParam("pageIndex") int pageIndex,
                                       @RequestParam("rd3session") String rd3session){
        return pubinferService.queryTrueInfers(pageIndex, rd3session);
    }
}
