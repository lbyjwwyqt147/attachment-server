package com.jwell.file.server.configuration;

import com.jwell.file.common.file.FileEnum;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    /**
     * 在配置文件中配置的文件保存路径
     */
    @Value("${data.file.dir}")
    private String  flieRelativePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //addResourceHandler是指你想通过url请求的路径
        //addResourceLocations是文件存放的真实路径
        registry.addResourceHandler("/" + FileEnum.IMAGES.name( )+ "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.IMAGES.name());
        registry.addResourceHandler("/" + FileEnum.DOCUMENTS.name() + "/**").addResourceLocations("file:" + flieRelativePath + "/" + FileEnum.DOCUMENTS.name());
        registry.addResourceHandler("/" + FileEnum.VIDEOS.name() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.VIDEOS.name());
        registry.addResourceHandler("/" + FileEnum.ZIPS.name() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.ZIPS.name());
        registry.addResourceHandler("/" + FileEnum.OTHERS.name() + "/**").addResourceLocations("file:"+ flieRelativePath + "/" + FileEnum.OTHERS.name());
        super.addResourceHandlers(registry);
    }

   /* @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new CustomRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        return handlerMapping;
    }*/
}
