package edu.qingtai.pubandcollect.service;

import edu.qingtai.pubandcollect.domain.User;
import net.sf.json.JSONObject;

public interface UserService {
    String saveUser(JSONObject userInfo, String userName, String userImage);

    boolean isExist(String openid);
}
