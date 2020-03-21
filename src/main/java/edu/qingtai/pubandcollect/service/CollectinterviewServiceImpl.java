package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectinterview;
import edu.qingtai.pubandcollect.domain.Pubinterview;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Interview;
import edu.qingtai.pubandcollect.mapper.CollectinterviewMapper;
import edu.qingtai.pubandcollect.mapper.PubinterviewMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectinterviewServiceImpl implements CollectinterviewService{
    private CollectinterviewMapper collectinterviewMapper;
    private PubinterviewMapper pubinterviewMapper;
    private RedisUtils redisUtils;
    private EventDispatcher eventDispatcher;

    @Autowired
    public CollectinterviewServiceImpl(final CollectinterviewMapper collectinterviewMapper,
                                   final PubinterviewMapper pubinterviewMapper,
                                   final EventDispatcher eventDispatcher,
                                   final RedisUtils redisUtils){
        this.collectinterviewMapper = collectinterviewMapper;
        this.pubinterviewMapper = pubinterviewMapper;
        this.eventDispatcher = eventDispatcher;
        this.redisUtils = redisUtils;
    }

    @Override
    public List<Pubinterview> queryInterviewFromOpenid(String rd3session){
        return pubinterviewMapper.selectInterviewByUuidList(
                collectinterviewMapper.selectUuidByOpenid(redisUtils.get(rd3session))
        );
    }

    @Override
    public void saveCollectInterview(String uuid, String rd3session){
        Collectinterview collectinterview = new Collectinterview();
        collectinterview.setOpenid(redisUtils.get(rd3session));
        collectinterview.setUuid(uuid);
        collectinterviewMapper.insert(collectinterview);
        Pubinterview pubinterview = pubinterviewMapper.selectByPrimaryKey(uuid);
        pubinterview.setFavorite(pubinterview.getFavorite() + 1);
        pubinterviewMapper.updateByPrimaryKey(pubinterview);
        eventDispatcher.sendInterview(new Interview(uuid, pubinterview.getFavorite()));
    }

    @Override
    public void deleteCollectInterview(String uuid, String rd3session){
        collectinterviewMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Pubinterview pubinterview = pubinterviewMapper.selectByPrimaryKey(uuid);
        pubinterview.setFavorite(pubinterview.getFavorite() - 1);
        pubinterviewMapper.updateByPrimaryKey(pubinterview);
        eventDispatcher.sendInterview(new Interview(uuid, pubinterview.getFavorite()));
    }
}
