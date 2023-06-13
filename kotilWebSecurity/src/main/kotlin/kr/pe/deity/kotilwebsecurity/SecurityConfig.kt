package kr.pe.deity.kotilwebsecurity

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@EnableWebSecurity
class SecurityConfig{

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        return http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/index.html",
                "/webjars/**",
                /* swagger v3 */
                "/v3/api-docs/**",
                "/swagger-ui/**").permitAll()
//                    .mvcMatchers("/spring3/api/lecture/**").permitAll()
            .anyRequest().permitAll()
            .and().build();
    }
}