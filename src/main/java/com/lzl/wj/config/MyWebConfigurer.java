package com.lzl.wj.config;

import com.lzl.wj.interceptor.LoginInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class MyWebConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("http://liuzile.com:8080","http://liuzile.com","http://localhost","http://localhost:8080","https://web.postman.co")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

    @Bean
    public LoginInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/logout")
                .excludePathPatterns("/api/books")
                .excludePathPatterns("/api/file/**")
                .excludePathPatterns("/api/getRecommend")
                .excludePathPatterns("/api/article/**")
                .excludePathPatterns("/api/register");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "d:/image/");
//        registry.addResourceHandler("/api/file/**").addResourceLocations("file:" + "/home/image");
    }
}
