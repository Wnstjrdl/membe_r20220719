package com.its.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private String connectPath="/upload/**";
    private String resourcePath="file:///D:/springboot_img/";
    @Override
    public  void  addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);
    }
}
