package edu.qingtai.pubandcollect.config;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerBeanMapperConfig {
    @Bean
    public Mapper mapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }
}
