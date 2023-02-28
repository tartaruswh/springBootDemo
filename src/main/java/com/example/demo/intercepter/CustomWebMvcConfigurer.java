
package com.example.demo.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类：对要进行拦截的请求功能类进行配置
 */
@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {

    //拦截请求注册，添加对应拦截请求类
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册要拦截的请求,addInterceptor传入的参数为拦截请求类对象,addPathPatterns传入的参数是拦截请求地址
        //即对对应类的某个请求地址业务进行拦截
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/api/v1/pri/**");
        registry.addInterceptor(getTwoInterceptor()).addPathPatterns("/api/v1/pub/**");
        WebMvcConfigurer.super.addInterceptors(registry);

    }
    @Bean
    public LoginInterceptor getLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Bean
    public Twonterceptor getTwoInterceptor() {
        return new Twonterceptor();
    }
}
