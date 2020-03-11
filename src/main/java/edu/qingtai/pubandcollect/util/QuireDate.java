package edu.qingtai.pubandcollect.util;

import java.sql.Date;

public class QuireDate {
    public static Date currentDate(){
        java.util.Date date = new java.util.Date();
        return new Date(date.getTime());
    }

    public static void main(String[] args) {
        System.out.println(currentDate());
    }
}
