package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectinfer;
import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Infer;
import edu.qingtai.pubandcollect.mapper.CollectinferMapper;
import edu.qingtai.pubandcollect.mapper.PubinferMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectinferServiceImpl implements CollectinferService{
    private CollectinferMapper collectinferMapper;
    private PubinferMapper pubinferMapper;
    private EventDispatcher eventDispatcher;
    private RedisUtils redisUtils;

    @Autowired
    public CollectinferServiceImpl(final CollectinferMapper collectinferMapper,
                                   final PubinferMapper pubinferMapper,
                                   final RedisUtils redisUtils,
                                   final EventDispatcher eventDispatcher){
        this.collectinferMapper = collectinferMapper;
        this.pubinferMapper = pubinferMapper;
        this.redisUtils = redisUtils;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void saveCollectInfer(String uuid, String rd3session){
        Collectinfer collectinfer = new Collectinfer();
        collectinfer.setUuid(uuid);
        collectinfer.setOpenid(redisUtils.get(rd3session));
        collectinferMapper.insert(collectinfer);
        Pubinfer pubinfer = pubinferMapper.selectByPrimaryKey(uuid);
        pubinfer.setFavorite(pubinfer.getFavorite() + 1);
        pubinferMapper.updateByPrimaryKey(pubinfer);
        eventDispatcher.sendInfer(new Infer(uuid, pubinfer.getFavorite()));
    }

    @Override
    public List<Pubinfer> queryInferFromOpenid(String rd3session){
        return pubinferMapper.selectInferByUuidList(
                collectinferMapper.selectUuidByOpenid(redisUtils.get(rd3session))
        );
    }

    @Override
    public void deleteCollectInfer(String uuid, String rd3session){
        collectinferMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Pubinfer pubinfer = pubinferMapper.selectByPrimaryKey(uuid);
        pubinfer.setFavorite(pubinfer.getFavorite() - 1);
        pubinferMapper.updateByPrimaryKey(pubinfer);
    }
}
