package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Collectinfer;
import edu.qingtai.pubandcollect.domain.Pubinfer;
import edu.qingtai.pubandcollect.domain.PubinferVo;
import edu.qingtai.pubandcollect.mapper.CollectinferMapper;
import edu.qingtai.pubandcollect.mapper.PubinferMapper;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectinferServiceImpl implements CollectinferService{
    private CollectinferMapper collectinferMapper;
    private PubinferMapper pubinferMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public CollectinferServiceImpl(final CollectinferMapper collectinferMapper,
                                   final PubinferMapper pubinferMapper,
                                   final RedisUtils redisUtils,
                                   final Mapper mapper){
        this.collectinferMapper = collectinferMapper;
        this.pubinferMapper = pubinferMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
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
    }

    @Override
    public List<PubinferVo> queryInferFromOpenid(String rd3session){
        List<Pubinfer> pubinferList = pubinferMapper.selectInferByUuidList(
                collectinferMapper.selectUuidByOpenid(redisUtils.get(rd3session)));

        List<PubinferVo> pubinferVoList = new ArrayList<>();

        if(pubinferList == null){
            return pubinferVoList;
        }else{
            for(Pubinfer pubinfer : pubinferList){
                PubinferVo pubinferVo = mapper.map(pubinfer, PubinferVo.class);
                pubinferVo.setCollect(Boolean.TRUE);
                pubinferVo.setLabels();
                pubinferVoList.add(pubinferVo);
            }
            return pubinferVoList;
        }
    }

    @Override
    public void deleteCollectInfer(String uuid, String rd3session){
        collectinferMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Pubinfer pubinfer = pubinferMapper.selectByPrimaryKey(uuid);
        pubinfer.setFavorite(pubinfer.getFavorite() - 1);
        pubinferMapper.updateByPrimaryKey(pubinfer);
    }
}
