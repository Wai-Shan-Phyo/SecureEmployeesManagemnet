package com.sem.project.common.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(
                                HttpMethod.POST , "/api/employees/**"


                        ).hasRole("ADMIN")
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/leaves")
                        .hasRole("EMPLOYEE")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/leaves/my")
                        .hasRole("EMPLOYEE")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/leaves/pending")
                        .hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/leaves/*/approve")
                        .hasRole("MANAGER")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/leaves/*/reject")
                        .hasRole("MANAGER")
                        .anyRequest()
                        .authenticated()
                )

                .oauth2ResourceServer(
                        oauth2 ->
                                oauth2.jwt(
                                        jwt -> jwt.jwtAuthenticationConverter( jwtAuthenticationConverter())
                                 )
                );

        return http.build();
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter =
                new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(
                new KeycloakRoleConverter());

        return converter;
    }
}
