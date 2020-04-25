package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectimpression;
import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;
import edu.qingtai.pubandcollect.mapper.CollectimpressionMapper;
import edu.qingtai.pubandcollect.mapper.PubimpressionMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectimpressionServiceImpl implements CollectimpressionService{
    private CollectimpressionMapper collectimpressionMapper;
    private PubimpressionMapper pubimpressionMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public CollectimpressionServiceImpl(final CollectimpressionMapper collectimpressionMapper,
                                       final PubimpressionMapper pubimpressionMapper,
                                       final RedisUtils redisUtils,
                                        final Mapper mapper){
        this.collectimpressionMapper = collectimpressionMapper;
        this.pubimpressionMapper = pubimpressionMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
    }

    @Override
    public void saveCollectImpression(String uuid, String rd3session){
        Collectimpression collectimpression = new Collectimpression();
        collectimpression.setOpenid(redisUtils.get(rd3session));
        collectimpression.setUuid(uuid);
        collectimpressionMapper.insert(collectimpression);
        Pubimpression pubimpression = pubimpressionMapper.selectByPrimaryKey(uuid);
        pubimpression.setFavorite(pubimpression.getFavorite() + 1);
        pubimpressionMapper.updateByPrimaryKey(pubimpression);
    }

    @Override
    public List<PubimpressionVo> queryImpressionFromOpenid(String rd3session){
        List<Pubimpression> pubimpressionList = pubimpressionMapper.selectImpressionByUuidList(
                collectimpressionMapper.selectUuidByOpenid(redisUtils.get(rd3session)));

        List<PubimpressionVo> pubimpressionVoList = new ArrayList<>();

        if(pubimpressionList == null){
            return pubimpressionVoList;
        }else{
            for(Pubimpression pubimpression : pubimpressionList){
                PubimpressionVo pubimpressionVo = mapper.map(pubimpression, PubimpressionVo.class);
                pubimpressionVo.setCollect(Boolean.TRUE);
                pubimpressionVo.setLabels();
                pubimpressionVoList.add(pubimpressionVo);
            }
            return pubimpressionVoList;
        }
    }

    @Override
    public void deleteCollectImpression(String uuid, String rd3session){
        collectimpressionMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Pubimpression pubimpression = pubimpressionMapper.selectByPrimaryKey(uuid);
        pubimpression.setFavorite(pubimpression.getFavorite() - 1);
        pubimpressionMapper.updateByPrimaryKey(pubimpression);
    }
}
