//package com.example.customerservice.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST ,"/customers").permitAll()
//                        .requestMatchers("/actuator/**").permitAll()
//                        .anyRequest().authenticated()
//
//                )
//                .csrf().disable()
//                .cors().disable()
//                .httpBasic().disable()
//                .oauth2ResourceServer().jwt();
//        return http.build();
//    }
//}