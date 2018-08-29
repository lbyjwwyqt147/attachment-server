package com.jwell.file.server.configuration;

import com.jwell.file.common.file.FileEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

/***
 *
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${data.file.dir}")
    private String  flieRelativePath;

    /**
     * 注册资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想通过url请求的路径
        //addResourceLocations是文件存放的真实路径
        registry.addResourceHandler("/" + FileEnum.IMAGES.getName()+ "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.IMAGES.getName() + "/");
        registry.addResourceHandler("/" + FileEnum.DOCUMENTS.getName() + "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.DOCUMENTS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.VIDEOS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.VIDEOS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.ZIPS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.ZIPS.getName()  + "/");
        registry.addResourceHandler("/" + FileEnum.OTHERS.getName() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.OTHERS.getName()  + "/");
        // 注册swagger 资源  不然访问swagger-ui.html 是404
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }


    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }
}
