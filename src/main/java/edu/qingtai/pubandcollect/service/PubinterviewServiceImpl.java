package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.domain.PubinterviewVo;
import edu.qingtai.pubandcollect.mapper.CollectinterviewMapper;
import edu.qingtai.pubandcollect.mapper.PubinterviewMapper;
import edu.qingtai.pubandcollect.util.ConstData;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.RedisUtils;
import edu.qingtai.pubandcollect.util.UploadImage;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PubinterviewServiceImpl implements PubinterviewService{
    private PubinterviewMapper pubinterviewMapper;
    private RedisUtils redisUtils;
    private CollectinterviewMapper collectinterviewMapper;
    private Mapper mapper;

    @Autowired
    public PubinterviewServiceImpl(final PubinterviewMapper pubinterviewMapper,
                                   final RedisUtils redisUtils,
                                   final CollectinterviewMapper collectinterviewMapper,
                                   final Mapper mapper){
        this.pubinterviewMapper = pubinterviewMapper;
        this.redisUtils = redisUtils;
        this.collectinterviewMapper = collectinterviewMapper;
        this.mapper = mapper;
    }

    @Override
    public void saveInterview(String title, String rd3session, String content,
                              String username, String userimage, String images){
        Pubinterview pubinterview = new Pubinterview();
        pubinterview.setTitle(title);
        pubinterview.setOpenid(redisUtils.get(rd3session));
        pubinterview.setContent(content);
//        String Images = UploadImage.uploadImagesOfInfer(ConstData.interviewLocation,fileList, pubinterview.getOpenid());
        pubinterview.setImages(images);
        pubinterview.setInserttime(QuireDate.currentDate());
        pubinterview.setUuid(UUID.randomUUID().toString().replace("-", ""));
        pubinterview.setUsername(username);
        pubinterview.setUserimage(userimage);
        pubinterviewMapper.insert(pubinterview);
    }

    @Override
    public List<Pubinterview> queryMyPublish(String rd3session){
        return pubinterviewMapper.selectMyPublish(redisUtils.get(rd3session));
    }

    @Override
    public void deleteInterview(String uuid){
        pubinterviewMapper.deleteByPrimaryKey(uuid);
        collectinterviewMapper.deleteByUuid(uuid);
    }

    @Override
    public List<Pubinterview> queryInterviews(int pageSize){
        int startIndex = (pageSize - 1) * ConstData.pageSize;
        List<String> uuidList = pubinterviewMapper.selectUuidByPageIndex(startIndex, ConstData.pageSize);
        if(uuidList.isEmpty()){
            return null;
        }
        return pubinterviewMapper.selectInterviewByUuidList(uuidList);
    }

    @Override
    public List<PubinterviewVo> queryTrueInterviews(int pageIndex, String rd3session){
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        List<Pubinterview> pubinterviewList = queryInterviews(pageIndex);

        List<String> uuidList = collectinterviewMapper.selectUuidByOpenid(redisUtils.get(rd3session));

        List<PubinterviewVo> pubinterviewVoList = new ArrayList<>();

        if(pubinterviewList == null){
            return pubinterviewVoList;
        }else{
            for(Pubinterview pubinterview : pubinterviewList){
                if(uuidList.contains(pubinterview.getUuid())){
                    PubinterviewVo pubinterviewVo = mapper.map(pubinterview, PubinterviewVo.class);
                    pubinterviewVo.setCollect(Boolean.TRUE);
                    pubinterviewVoList.add(pubinterviewVo);
                }else{
                    pubinterviewVoList.add(mapper.map(pubinterview, PubinterviewVo.class));
                }
            }

            return pubinterviewVoList;
        }
    }
}
