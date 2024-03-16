package com.congsole.SimpleSNS.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthenticationConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(registry ->
                        registry.requestMatchers("/api/*/users/join", "/api/*/users/login").permitAll()
                )
                .authorizeRequests(registry ->
                        registry.requestMatchers("/api/**").authenticated()
                )
                .sessionManagement(sessionManagement ->
                    sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
//                .exceptionHandling(eh -> eh.authenticationEntryPoint())
                // TODO
        ;
        return http.build();
    }
}
