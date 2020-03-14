package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinfer;
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

    @Autowired
    public PubinferServiceImpl(final PubinferMapper pubinferMapper,
                               final RedisUtils redisUtils){
        this.pubinferMapper = pubinferMapper;
        this.redisUtils = redisUtils;
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
        pubinferMapper.insert(pubinfer);
    }
}
