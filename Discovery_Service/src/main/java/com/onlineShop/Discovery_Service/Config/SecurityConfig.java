package com.onlineShop.Discovery_Service.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.ignoringRequestMatchers("/eureka/**")) // Correct way
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/eureka/**").permitAll() // Allow access
                        .anyRequest().authenticated()
                );

        return httpSecurity.build();
    }


}
