package kr.pe.deity.kotilwebsecurity

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class StaticRoutingConfigure: WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry){
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/")
        registry.addResourceHandler("index.html").addResourceLocations("classpath:/static/docs/")
    }
}