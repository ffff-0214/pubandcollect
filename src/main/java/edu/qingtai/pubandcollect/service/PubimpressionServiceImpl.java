package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.domain.PubimpressionVo;
import edu.qingtai.pubandcollect.mapper.CollectimpressionMapper;
import edu.qingtai.pubandcollect.mapper.PubimpressionMapper;
import edu.qingtai.pubandcollect.util.ConstData;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PubimpressionServiceImpl implements PubimpressionService{
    private PubimpressionMapper pubimpressionMapper;
    private CollectimpressionMapper collectimpressionMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public PubimpressionServiceImpl(final PubimpressionMapper pubimpressionMapper,
                                    final RedisUtils redisUtils,
                                    final CollectimpressionMapper collectimpressionMapper,
                                    final Mapper mapper){
        this.pubimpressionMapper = pubimpressionMapper;
        this.redisUtils = redisUtils;
        this.collectimpressionMapper = collectimpressionMapper;
        this.mapper = mapper;
    }

    @Override
    public void saveImpression(String position, String company, String workPlace, String education, String salary,
                               String label, String content, String rd3session, String username, String userimage){
        Pubimpression pubimpression = new Pubimpression();
        pubimpression.setPosition(position);
        pubimpression.setCompany(company);
        pubimpression.setWorkplace(workPlace);
        pubimpression.setEducation(education);
        pubimpression.setSalary(salary);
        pubimpression.setLabel(label);
        pubimpression.setContent(content);
        pubimpression.setOpenid(redisUtils.get(rd3session));
        pubimpression.setInserttime(QuireDate.currentDate());
        pubimpression.setUuid(UUID.randomUUID().toString().replace("-", ""));
        pubimpression.setUsername(username);
        pubimpression.setUserimage(userimage);
        pubimpressionMapper.insert(pubimpression);

    }

    @Override
    public List<Pubimpression> queryMyPublish(String rd3session){
        return pubimpressionMapper.selectMyPublish(redisUtils.get(rd3session));
    }

    @Override
    public void deleteImpression(String uuid){
        pubimpressionMapper.deleteByPrimaryKey(uuid);
        collectimpressionMapper.deleteByUuid(uuid);
    }

    @Override
    public List<Pubimpression> queryImpressions(int pageIndex){
        //前端的页面从1开始
        int startIndex = (pageIndex - 1) * ConstData.pageSize;
        List<String> uuidList = pubimpressionMapper.selectUuidByPageIndex(startIndex, ConstData.pageSize);
        if(uuidList.isEmpty()){
            return null;
        }
        return pubimpressionMapper.selectImpressionByUuidList(uuidList);
    }

    @Override
    public List<PubimpressionVo> queryTrueImpressions(int pageIndex, String rd3session){
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        List<Pubimpression> pubimpressionList = queryImpressions(pageIndex);

        List<String> uuidList = collectimpressionMapper.selectUuidByOpenid(redisUtils.get(rd3session));

        List<PubimpressionVo> pubimpressionVoList = new ArrayList<>();

        if(pubimpressionList == null){
            return pubimpressionVoList;
        }else{
            for(Pubimpression pubimpression : pubimpressionList){
                if(uuidList.contains(pubimpression.getUuid())){
                    PubimpressionVo pubimpressionVo = mapper.map(pubimpression, PubimpressionVo.class);
                    pubimpressionVo.setCollect(Boolean.TRUE);
                    pubimpressionVoList.add(pubimpressionVo);
                }else{
                    pubimpressionVoList.add(mapper.map(pubimpression, PubimpressionVo.class));
                }
            }

            return pubimpressionVoList;
        }

    }
}
