package org.example.userserviceaugmorning.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//  https://spring.io/guides/gs/securing-web

@Configuration
public class SecurityConfigurations {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> {
                        try {
                            requests
                                    .anyRequest().permitAll()
                                    .and().cors().disable()
                                    .csrf().disable();
                        } catch (Exception e) {

                        }
                    }
                );

        return http.build();
    }
}
