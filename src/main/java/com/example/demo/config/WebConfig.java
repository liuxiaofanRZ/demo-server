package com.example.demo.config;


import com.example.demo.interceptor.LoginInterceptor;
import org.aopalliance.intercept.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor());
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**"); // 允许跨域访问的路径
//                .allowedOrigins("*") // 允许跨域访问的 源
//                .allowedMethods()
//                .maxAge(168000) // 预检（options）间隔时间
//                .allowedHeaders("*") // 允许头部设置
//                .allowCredentials(true); // 是否发送cookie
    }
}
