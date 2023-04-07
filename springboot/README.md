

### 단점
    1. maven으로 만들었다.
    2. spring 1.x, 2.x버전만 된다. 
       - 3.x버전은 따로 해야한다.

### 만들어 놓은 것 : 볼게 없는데... 내가 못보는 것인가?
```text
springdoc-openapi-common
springdoc-openapi-data-rest
springdoc-openapi-groovy
springdoc-openapi-hateoas
springdoc-openapi-javadoc
springdoc-openapi-kotlin
springdoc-openapi-security
springdoc-openapi-ui
springdoc-openapi-webflux-core
springdoc-openapi-webflux-ui
springdoc-openapi-webmvc-core
```

### 스프링 3.x사용
https://springdoc.org/v2/
openapi 2.x를 쓰라는 말이다.. 실험버전인것같다. 아니 preview 상태일려나?
-> 빌드가 실패하고 있는데.. 왜그러지?
-> 왜 security 2.7과 3.0이 같이 있을까?
This library supports:
  - OpenAPI 3
  - Spring-boot v3 (Java 17 & Jakarta EE 9)
  - JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
  - Swagger-ui
  - OAuth 2
  - GraalVM native images