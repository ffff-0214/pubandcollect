package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.User;
import edu.qingtai.pubandcollect.mapper.UserMapper;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService{
    private UserMapper userMapper;

    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UserServiceImpl(final UserMapper userMapper,
                           final StringRedisTemplate stringRedisTemplate){
        this.userMapper = userMapper;
        this.stringRedisTemplate=stringRedisTemplate;
    }

    @Override
    public String saveUser(JSONObject userInfo, String userName, String userImage){
        String rd3session = DigestUtils.md5DigestAsHex((userInfo.getString("openid")+
        userInfo.getString("session_key")).getBytes());

        String openid = userInfo.getString("openid");

        //看redis
        if(stringRedisTemplate.opsForValue().get(rd3session).isEmpty()){
            stringRedisTemplate.opsForValue().set(rd3session, openid, 1, TimeUnit.DAYS);
        }
        else{
            stringRedisTemplate.opsForValue().set(rd3session, openid, 1, TimeUnit.DAYS);
            return rd3session;
        }

        //看数据库

        if(userMapper.selectByPrimaryKey(openid) == null){
            User user = new User();
            user.setOpenid(openid);
            user.setUsername(userName);
            user.setUserimage(userImage);
            userMapper.insert(user);
        }

        return rd3session;
    }

    @Override
    public boolean isExist(String openid){
        return (userMapper.selectByPrimaryKey(openid) != null);
    }
}
