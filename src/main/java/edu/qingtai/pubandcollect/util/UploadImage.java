package edu.qingtai.pubandcollect.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UploadImage {

    private static String createNum(){
        return String.valueOf((int)(Math.random()*100+1));
    }

    public static String uploadImagesOfInfer(String location, List<MultipartFile> fileList, String openid){
        List<String> images = new ArrayList<>();

        for(MultipartFile file : fileList){
            if(file.isEmpty()){
                continue;
            }
            try{
                String fileName =location + openid + "_" +createNum() + ".jpg";
                //File 类是对文件系统的映射 并不是硬盘上真实的文件
                File dest = new File(fileName);
                //判断映射的文件是否真实存在
                while(dest.exists()){
                    fileName = location + openid + "_" +createNum() + ".jpg";
                    dest = new File(fileName);
                }
                //如果存在即可直接操作,  否则需要调用  file.createNewFile() 创建真实文件
                //但是以上方式只会适用创建文件本身,不包括父文件的创建(如果父文件不存在)
                file.transferTo(dest);
//                images.add(fileName);
                images.add(fileName.replace(location, "http://47.95.39.132:80/"+ location.split("/")[1] +"/"));
            }catch (IllegalArgumentException | IOException e){
                e.printStackTrace();
                return "";
            }
        }
        return StringUtils.join(images, ",");
    }

    public static void main(String[] args) {
//        List<String> a = new ArrayList<>();
////        a.add("1");
//        for (String i: a) {
//            System.out.println(i);
//        }
        String location = ConstData.interviewLocation;
        String fileName = location + "1.jpg";
        String images = fileName.replace(location, "http://47.95.39.132:80/"+ location.split("/")[3] +"/");
        System.out.println(fileName);
        System.out.println(images);
    }
}
