package edu.qingtai.pubandcollect.controller;

import edu.qingtai.pubandcollect.service.UserService;
import edu.qingtai.pubandcollect.util.ConstData;
import edu.qingtai.pubandcollect.util.HttpUrlConnection;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(final UserService userService){
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public String UserLogin(@RequestParam("userName") String userName,
                          @RequestParam("userImage") String userImage,
                          @RequestParam("code") String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        url = url.replace("APPID", ConstData.APPID).replace("SECRET", ConstData.SECRET)
                .replace("JSCODE", code);

        JSONObject userInfo = JSONObject.fromObject(HttpUrlConnection.httpUrlGet(url));

        return userService.saveUser(userInfo, userName, userImage);
    }

    @GetMapping(value = "/yhf")
    public void test(){
        userService.test();
    }


}
