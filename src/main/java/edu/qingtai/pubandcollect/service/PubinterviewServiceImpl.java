package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Interview;
import edu.qingtai.pubandcollect.mapper.CollectinterviewMapper;
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
    private EventDispatcher eventDispatcher;
    private CollectinterviewMapper collectinterviewMapper;

    @Autowired
    public PubinterviewServiceImpl(final PubinterviewMapper pubinterviewMapper,
                                   final RedisUtils redisUtils,
                                   final EventDispatcher eventDispatcher,
                                   final CollectinterviewMapper collectinterviewMapper){
        this.pubinterviewMapper = pubinterviewMapper;
        this.redisUtils = redisUtils;
        this.eventDispatcher = eventDispatcher;
        this.collectinterviewMapper = collectinterviewMapper;
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
        if(pubinterviewMapper.insert(pubinterview) >= 0){
            eventDispatcher.sendInterview(
                    new Interview(pubinterview.getUuid(),
                            pubinterview.getTitle(),
                            pubinterview.getInserttime(),
                            pubinterview.getFavorite(),
                            pubinterview.getImages(),
                            pubinterview.getContent())
            );
        }
    }

    @Override
    public List<Pubinterview> queryMyPublish(String rd3session){
        return pubinterviewMapper.selectMyPublish(redisUtils.get(rd3session));
    }

    @Override
    public void deleteInterview(String uuid){
        pubinterviewMapper.deleteByPrimaryKey(uuid);
        collectinterviewMapper.deleteByUuid(uuid);
        eventDispatcher.sendInterview(new Interview(uuid));
    }
}
