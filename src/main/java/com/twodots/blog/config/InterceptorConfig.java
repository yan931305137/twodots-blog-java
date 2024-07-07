package com.twodots.blog.config;

import com.twodots.blog.util.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * InterceptorConfig
 *
 * @ date 2024/7/5 22:09
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //        跨域拦截器
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        //        校验token拦截器
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/*/admin/**");
    }
}
