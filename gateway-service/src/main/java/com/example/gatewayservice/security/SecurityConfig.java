//package com.example.gatewayservice.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.config.web.server.ServerHttpSecurity;
//import org.springframework.security.web.server.SecurityWebFilterChain;
//
//
//@Configuration
//@EnableWebFluxSecurity
//public class SecurityConfig {
//    @Bean
//    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
//        http.authorizeExchange(
//                exchanges->exchanges
//                        .pathMatchers("/actuator/**").permitAll()
//                        .pathMatchers(HttpMethod.GET,"/vehicles/**").permitAll()
//                        .pathMatchers(HttpMethod.GET,"/categories/**").permitAll()
//                        .pathMatchers(HttpMethod.POST,"/reservations/**").permitAll()
//                        // user isn't require authentication
//                        .pathMatchers(HttpMethod.POST,"/customers/**").permitAll()
//                        .pathMatchers(HttpMethod.GET,"/offices/**").permitAll()
//                        .pathMatchers(HttpMethod.GET,"/categories/**").permitAll()
//                        .anyExchange().permitAll()
//
//        ).oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
//        http.
//                csrf().disable()
//                .httpBasic().disable();
//        return http.build();
//    }
//}
