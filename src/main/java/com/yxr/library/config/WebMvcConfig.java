package com.yxr.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述
 * @作者 yxr
 * @日期 3/27/2020 2:31 PM
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> list =  new ArrayList<String>();
        list.add("/user/toLogin"); //请求的地址
        list.add("/user/login"); //请求的地址
        list.add("/user/checkPhone"); //请求的地址
        list.add("/user/checkEmail"); //请求的地址
        list.add("/user/toRegister"); //注册
        list.add("/user/register"); //去注册页面

        list.add("/static/css/**"); //请求的地址
        list.add("/static/js/**"); //请求的地址
        list.add("/static/img/**"); //请求的地址
        list.add("/static/bootstrap-datetimepicker/**"); //请求的地址
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").
                excludePathPatterns(list);

    }

    @Value("${file.upload.path}")
    private String filePath ;
    /*相对路径*/
    @Value("${file.upload.relative}")
    private String relativePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(relativePath).addResourceLocations("file:/"+filePath);
    }

}
