package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.Pubimpression;
import edu.qingtai.pubandcollect.event.EventDispatcher;
import edu.qingtai.pubandcollect.event.Impression;
import edu.qingtai.pubandcollect.mapper.PubimpressionMapper;
import edu.qingtai.pubandcollect.util.QuireDate;
import edu.qingtai.pubandcollect.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PubimpressionServiceImpl implements PubimpressionService{
    private PubimpressionMapper pubimpressionMapper;
    private RedisUtils redisUtils;
    private EventDispatcher eventDispatcher;

    @Autowired
    public PubimpressionServiceImpl(final PubimpressionMapper pubimpressionMapper,
                                    final RedisUtils redisUtils,
                                    final EventDispatcher eventDispatcher){
        this.pubimpressionMapper = pubimpressionMapper;
        this.redisUtils = redisUtils;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void saveImpression(String position, String company, String workPlace, String education, String salary,
                               String label, String content, String rd3session){
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
        if(pubimpressionMapper.insert(pubimpression) >= 0){
            eventDispatcher.sendImpression(
                    new Impression(pubimpression.getUuid(),
                            pubimpression.getPosition(),
                            pubimpression.getCompany(),
                            pubimpression.getWorkplace(),
                            pubimpression.getEducation(),
                            pubimpression.getSalary(),
                            pubimpression.getInserttime(),
                            pubimpression.getLabel(),
                            pubimpression.getTruth(),
                            pubimpression.getContent())
            );
        }
    }
}
