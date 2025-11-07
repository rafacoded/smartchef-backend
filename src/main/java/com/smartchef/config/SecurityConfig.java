package com.smartchef.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF para pruebas con Postman/Ionic
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/**").permitAll() // permitir todos los endpoints
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form.disable()) // sin login todavía
                .httpBasic(basic -> basic.disable()); // sin basic auth

        return http.build();
    }
}
