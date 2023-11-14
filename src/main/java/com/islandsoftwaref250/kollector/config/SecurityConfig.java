package com.islandsoftwaref250.kollector.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(
                        registry -> {
                            registry.requestMatchers("/api/v1/products/").permitAll();
                            registry.anyRequest().authenticated();
                        }
                )
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/h2-console/**"))
                .formLogin(Customizer.withDefaults())
                .build();
    }
}

