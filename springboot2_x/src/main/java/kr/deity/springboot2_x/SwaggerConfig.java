package kr.deity.springboot2_x;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;

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

    @Bean
    public OpenApiCustomiser consumerTypeHeaderOpenAPICustomiser() {
        return openApi -> openApi.getPaths().values().stream().flatMap(pathItem -> pathItem.readOperations().stream())
                .forEach(operation -> operation.getResponses().addApiResponse("500", new ApiResponse().description("FAIL")
                        .content(new Content().addMediaType("application/json",
                                new MediaType().schema(new Schema().$ref("#/components/schemas/BaseResponse"))))));
    }



}
