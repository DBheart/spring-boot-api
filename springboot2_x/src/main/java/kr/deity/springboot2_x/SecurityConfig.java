package kr.deity.springboot2_x;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            return http.csrf().disable()
                    .authorizeRequests()
//                    .mvcMatchers("/spring3/api//lecture/**").permitAll()
                    .anyRequest().permitAll()
                .and().build();
    }
}
