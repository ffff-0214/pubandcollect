package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferRec;
import edu.qingtai.pubandcollect.domain.PubinferVo;
import edu.qingtai.pubandcollect.domain.PubinferVoDetail;
import edu.qingtai.pubandcollect.service.PubinferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/infer")
public class PubinferController {
    private PubinferService pubinferService;

    @Autowired
    public PubinferController(final PubinferService pubinferService){
        this.pubinferService = pubinferService;
    }

//    @RequestParam("title") String title,
//    @RequestParam("label") String label,
//    @RequestParam("rd3session") String rd3session,
//    @RequestParam("content") String content,
//    @RequestParam("username") String username,
//    @RequestParam("userimage") String userimage,
//    @RequestParam("images") String images

    @PostMapping
    public void Publish(@RequestBody PubinferRec pubinferRec){
        pubinferService.saveInfer(pubinferRec.getTitle(),
                pubinferRec.getLabel(), pubinferRec.getRd3session(),
                pubinferRec.getContent(), pubinferRec.getUsername(),
                pubinferRec.getUserimage(), pubinferRec.getImages());
    }

    @GetMapping(value = "/myself")
    public List<PubinferVo> queryMyPublish(@RequestParam("rd3session") String rd3session){
        return pubinferService.queryMyPublish(rd3session);
    }

    @DeleteMapping
    //uuid: uuid
    public void deleteInfer(@RequestBody Map<String,String> uuid){
        pubinferService.deleteInfer(uuid.get("uuid"));
    }

    @GetMapping(value = "/content")
    public PubinferVoDetail getInferContent(@RequestParam("uuid") String uuid,
                                            @RequestParam("rd3session") String rd3session){
        return pubinferService.queryContent(uuid, rd3session);
    }

    @GetMapping
    public List<PubinferVo> queryInfer(@RequestParam("pageIndex") int pageIndex,
                                       @RequestParam("rd3session") String rd3session){
        return pubinferService.queryTrueInfers(pageIndex, rd3session);
    }
}
