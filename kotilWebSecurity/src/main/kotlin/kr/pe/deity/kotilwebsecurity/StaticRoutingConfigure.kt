package kr.pe.deity.kotilwebsecurity

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

class StaticRoutingConfigure: WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/static/swagger-ui/")


//        registry.addResourceHandler("index.html").addResourceLocations("classpath:/static/docs/");

    }
}