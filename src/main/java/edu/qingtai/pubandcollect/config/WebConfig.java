package edu.qingtai.pubandcollect.config;

//import edu.qingtai.pubandcollect.interceptor.CorsInterceptor;
import edu.qingtai.pubandcollect.interceptor.VerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public VerifyInterceptor getVerifyInterceptor(){
        return new VerifyInterceptor();
    }

//    @Bean
//    public CorsInterceptor getCorsInterceptor(){
//        return new CorsInterceptor();
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(getCorsInterceptor())
//                .addPathPatterns("/**");
        registry.addInterceptor(getVerifyInterceptor())
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
        .allowedOrigins("*").allowCredentials(true).allowedMethods("*");
    }
}
