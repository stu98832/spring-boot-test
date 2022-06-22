package com.example.demo.Configures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.demo.Intercepters.DemoInterceptor;

@Configuration
public class DemoConfigure implements WebMvcConfigurer {
    @Autowired
    private DemoInterceptor demoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(demoInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}