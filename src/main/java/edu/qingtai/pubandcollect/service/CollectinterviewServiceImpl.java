package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectinterview;
import edu.qingtai.pubandcollect.mapper.CollectinterviewMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectinterviewServiceImpl implements CollectinterviewService{
    private CollectinterviewMapper collectinterviewMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectinterviewServiceImpl(final CollectinterviewMapper collectinterviewMapper,
                                   final RedisUtils redisUtils){
        this.collectinterviewMapper = collectinterviewMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public void collectInterview(String uuid, String rd3session){
        Collectinterview collectinterview = new Collectinterview();
        collectinterview.setOpenid(redisUtils.get(rd3session));
        collectinterview.setUuid(uuid);
        collectinterviewMapper.insert(collectinterview);
    }
}
