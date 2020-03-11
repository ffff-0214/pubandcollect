package edu.qingtai.pubandcollect.util;


import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUrlConnection {
    public static String httpUrlGet(String wxUrl){
        try{
            URL url = new URL(wxUrl);//把字符串转换为URL地址
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();//打开链接
            connection.connect();//连接会话
            //获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null){//循环读取流
                sb.append(line);
            }
            br.close();
            connection.disconnect();
            return sb.toString();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Url获取失败");
        }
        return null;
    }

    public static void main(String[] args) {
        String userInfo = httpUrlGet("https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code");
        JSONObject user = JSONObject.fromObject(userInfo);
        System.out.println(user);
        System.out.println(user.getString("errcode"));
        System.out.println(user.getString("errmsg"));
    }
}
