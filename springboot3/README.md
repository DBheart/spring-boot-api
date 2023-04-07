우선 결론적으로 spring boot 3와 swagger의 도입은 실패했다.
스프링부트에서 간단하게 쓰기위한 spenapi 2.1을쓰고
스프링부트 3.0부터는 시큐리티 버전도 올라가는데 이것의 코어가 6.02가
되면서 사용법이 변했다. 복합적인 이유가 있는 것같다.

실패의 사유는 get은 호출이 되는데 post가 안된다.
아마 get은 필터를 안걸어도 그냥 호출되는데 post와 같은 트랜잭션이 발생하는 것은 막히는 것같다.
설정을 막았는데도 안된다. 

1. 나의 스프링 시큐리티 지식이 짧다
2. 나의 스웨거 관련 지식이 짧다.

### 적용 라이브러리
springdoc-openapi v2.1.0
springboot 3.0.5
spring-security 3.0.5
- spring security 6.02

여기는 스웨거 버전을 쓰기위해서 만든다.

스프링에서 스웨거를 쓰기위해서는 openapi를 사용해야한다.
github에 가보니깐 많은 기본 템플릿이 있었다. 이걸로 활용해도 될정도이다.
https://github.com/springdoc/springdoc-openapi

### open api 2.1에서 제공하는 것
This library supports:
- OpenAPI 3
- Spring-boot (v1 and v2)
- JSR-303, specifically for @NotNull, @Min, @Max, and @Size.
- Swagger-ui
- Oauth 2

### 시큐리티 6.02
authorizeRequests는 이제 안쓴다.

```java
// 아래와 같이 바뀌었다.
//        .authorizeHttpRequests(request -> request
//              .anyRequest().permitAll()
//        );

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable()
                .authorizeHttpRequests(request -> request
                        .anyRequest().permitAll()
                );

        return http.build();
    }
}
```