package edu.qingtai.pubandcollect.config;

import edu.qingtai.pubandcollect.interceptor.VerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public VerifyInterceptor getVerifyInterceptor(){
        return new VerifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getVerifyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login").excludePathPatterns("/user/yhf");
    }
}
