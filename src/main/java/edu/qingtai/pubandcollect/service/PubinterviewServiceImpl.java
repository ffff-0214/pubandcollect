package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.mapper.PubinterviewMapper;
import edu.qingtai.pubandcollect.util.ConstData;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.RedisUtils;
import edu.qingtai.pubandcollect.util.UploadImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class PubinterviewServiceImpl implements PubinterviewService{
    private PubinterviewMapper pubinterviewMapper;
    private RedisUtils redisUtils;

    @Autowired
    public PubinterviewServiceImpl(final PubinterviewMapper pubinterviewMapper,
                                   final RedisUtils redisUtils){
        this.pubinterviewMapper = pubinterviewMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public void saveInterview(String title, String rd3session, String content, List<MultipartFile> fileList){
        Pubinterview pubinterview = new Pubinterview();
        pubinterview.setTitle(title);
        pubinterview.setOpenid(redisUtils.get(rd3session));
        pubinterview.setContent(content);
        String Images = UploadImage.uploadImagesOfInfer(ConstData.interviewLocation,fileList, pubinterview.getOpenid());
        pubinterview.setImages(Images);
        pubinterview.setInserttime(QuireDate.currentDate());
        pubinterview.setUuid(UUID.randomUUID().toString().replace("-", ""));
        pubinterviewMapper.insert(pubinterview);
    }
}
