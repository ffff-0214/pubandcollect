package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectimpression;
import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.mapper.CollectimpressionMapper;
import edu.qingtai.pubandcollect.mapper.PubimpressionMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectimpressionServiceImpl implements CollectimpressionService{
    private CollectimpressionMapper collectimpressionMapper;
    private PubimpressionMapper pubimpressionMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectimpressionServiceImpl(final CollectimpressionMapper collectimpressionMapper,
                                       final PubimpressionMapper pubimpressionMapper,
                                       final RedisUtils redisUtils){
        this.collectimpressionMapper = collectimpressionMapper;
        this.pubimpressionMapper = pubimpressionMapper;
        this.redisUtils = redisUtils;
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
    public List<Pubimpression> queryImpressionFromOpenid(String rd3session){
        return pubimpressionMapper.selectImpressionByUuidList(
                collectimpressionMapper.selectUuidByOpenid(redisUtils.get(rd3session))
        );
    }

    @Override
    public void deleteCollectImpression(String uuid, String rd3session){
        collectimpressionMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Pubimpression pubimpression = pubimpressionMapper.selectByPrimaryKey(uuid);
        pubimpression.setFavorite(pubimpression.getFavorite() - 1);
        pubimpressionMapper.updateByPrimaryKey(pubimpression);
    }
}
