package edu.qingtai.pubandcollect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

@SpringBootApplication
@MapperScan(basePackages = "edu.qingtai.pubandcollect.mapper", annotationClass = Repository.class)
public class PubandcollectApplication {

    public static void main(String[] args) {
        SpringApplication.run(PubandcollectApplication.class, args);
    }

}
