package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectimpression;
import edu.qingtai.pubandcollect.mapper.CollectimpressionMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectimpressionServiceImpl implements CollectimpressionService{
    private CollectimpressionMapper collectimpressionMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectimpressionServiceImpl(final CollectimpressionMapper collectimpressionMapper,
                                       final RedisUtils redisUtils){
        this.collectimpressionMapper = collectimpressionMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public void collectImpression(String uuid, String rd3session){
        Collectimpression collectimpression = new Collectimpression();
        collectimpression.setOpenid(redisUtils.get(rd3session));
        collectimpression.setUuid(uuid);
        collectimpressionMapper.insert(collectimpression);
    }
}
