package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectinfer;
import edu.qingtai.pubandcollect.mapper.CollectinferMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectinferServiceImpl implements CollectinferService{
    private CollectinferMapper collectinferMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectinferServiceImpl(final CollectinferMapper collectinferMapper,
                                   final RedisUtils redisUtils){
        this.collectinferMapper = collectinferMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public void collectInfer(String uuid, String rd3session){
        Collectinfer collectinfer = new Collectinfer();
        collectinfer.setUuid(uuid);
        collectinfer.setOpenid(redisUtils.get(rd3session));
        collectinferMapper.insert(collectinfer);
    }
}
