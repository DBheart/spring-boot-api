package kr.pe.deity.kotilwebsecurity

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class SwaggerConfig {

//    @Bean
//    fun openAPI(): OpenAPI{
//        val jwtSchemeName = "basicAuth"
//
//        val securityRequirement = SecurityRequirement().addList(jwtSchemeName);
//        // SecuritySchemes 등록
//        val components = Components()
//            .addSecuritySchemes(jwtSchemeName, SecurityScheme()
//                .name(jwtSchemeName)
//                .type(SecurityScheme.Type.HTTP) // HTTP 방식
//                .scheme("basic")
//                .bearerFormat("basicAuth")); // 토큰 형식을 지정하는 임의의 문자(Optional)
////                        .scheme("bearer")
////                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)
//
//
//        return OpenAPI().info(apiInfo()).addSecurityItem(securityRequirement)
//            .components(components);
//    }
//
//    fun apiInfo(): Info {
//        return Info()
//            .title("SpringBoot2 kotlin swagger API Documentation")
//            .description("springboot2 kotlin rest api practice.")
//            .version("0.1");
//    }
}