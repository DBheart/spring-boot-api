package kr.deity.springboot2_x;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI(){

        String jwtSchemeName = "basicAuth";

        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("basic")
                        .bearerFormat("basicAuth")); // 토큰 형식을 지정하는 임의의 문자(Optional)
//                        .scheme("bearer")
//                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)


        return new OpenAPI().info(apiInfo()).addSecurityItem(securityRequirement)
                .components(components);

    }
    public Info apiInfo() {
        return new Info()
                .title("SpringBoot2 pure swagger API Documentation")
                .description("springboot2 rest api practice.")
                .version("0.1");

    }
}
