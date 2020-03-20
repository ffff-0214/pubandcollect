package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Infer;
import edu.qingtai.pubandcollect.mapper.PubinferMapper;
import edu.qingtai.pubandcollect.util.ConstData;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.RedisUtils;
import edu.qingtai.pubandcollect.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class PubinferServiceImpl implements PubinferService{
    private PubinferMapper pubinferMapper;
    private RedisUtils redisUtils;
    private EventDispatcher eventDispatcher;

    @Autowired
    public PubinferServiceImpl(final PubinferMapper pubinferMapper,
                               final RedisUtils redisUtils,
                               final EventDispatcher eventDispatcher){
        this.pubinferMapper = pubinferMapper;
        this.redisUtils = redisUtils;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void saveInfer(String title, String label, String rd3session, String content, List<MultipartFile> fileList){
        Pubinfer pubinfer = new Pubinfer();
        pubinfer.setTitle(title);
        pubinfer.setLabel(label);
        pubinfer.setOpenid(redisUtils.get(rd3session));
        pubinfer.setContent(content);
        String images = UploadImage.uploadImagesOfInfer(ConstData.inferLocation, fileList, pubinfer.getOpenid());
        pubinfer.setImages(images);
        pubinfer.setInserttime(QuireDate.currentDate());
        pubinfer.setUuid(UUID.randomUUID().toString().replace("-", ""));
        if(pubinferMapper.insert(pubinfer) >=0 ){
            eventDispatcher.sendInfer(
                    new Infer(pubinfer.getUuid(),
                            pubinfer.getTitle(),
                            pubinfer.getImages(),
                            pubinfer.getInserttime(),
                            pubinfer.getLabel(),
                            pubinfer.getFavorite(),
                            pubinfer.getContent())
            );
        }
    }
}
