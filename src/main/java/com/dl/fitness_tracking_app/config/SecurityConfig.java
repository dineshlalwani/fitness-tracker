package com.dl.fitness_tracking_app.config;

import com.dl.fitness_tracking_app.jwt.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.dl.fitness_tracking_app.entity.Permission.*;
import static com.dl.fitness_tracking_app.entity.Role.ADMIN;
import static com.dl.fitness_tracking_app.entity.Role.USER;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthfilter;
    private final AuthenticationProvider authenticationProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/v1/auth/authenticate").permitAll()  // Directly matching /authenticate

                                .requestMatchers("/api/v1/auth/**").permitAll()
                                .requestMatchers("/api/v1/test").permitAll()

                                .requestMatchers("/api/v1/secured/user/**").hasAnyRole(USER.name())
                                .requestMatchers("/api/v1/secured/user/**").hasAuthority(USER_CREATE.name())
                                .requestMatchers("/api/v1/secured/user/**").hasAuthority(USER_DELETE.name())
                                .requestMatchers("/api/v1/secured/user/**").hasAuthority(USER_UPDATE.name())

                                .requestMatchers("/api/v1/secured/admin/**").hasAnyRole(ADMIN.name())
                                .requestMatchers("/api/v1/secured/admin/**").hasAuthority(ADMIN_CREATE.name())
                                .requestMatchers("/api/v1/secured/admin/**").hasAuthority(ADMIN_DELETE.name())
                                .requestMatchers("/api/v1/secured/admin/**").hasAuthority(ADMIN_UPDATE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthfilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
