package com.example.demo.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ContentSecurityPolicyHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

            .headers(headers -> headers
            	.addHeaderWriter(new ContentSecurityPolicyHeaderWriter("default-src 'self' http://localhost:8081; script-src 'self' http://localhost:8081; style-src 'self' http://localhost:8081;"))
            )
            .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                // 允許對 /api/** 路徑的無限制訪問
                .requestMatchers("/api/**").permitAll()
                // 其他所有請求需要身份驗證
                .anyRequest().authenticated()
            )
        	.csrf(csrf -> csrf.disable());

        return http.build();
    }
}

