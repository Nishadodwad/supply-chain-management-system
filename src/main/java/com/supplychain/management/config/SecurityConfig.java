package com.supplychain.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.context.annotation.Bean;
import com.supplychain.management.config.JwtFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // PUBLIC APIs
                        .requestMatchers(
                                "/auth/**",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()

                        // PRODUCTS
                        .requestMatchers(HttpMethod.GET, "/products/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/products/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/products/**").hasRole("ADMIN")

                        // USER PROFILE (MUST COME BEFORE /users/**)
                        .requestMatchers("/users/me")
                        .hasAnyRole("USER", "ADMIN")

                        // USER ORDERS (MUST COME BEFORE /orders/*)
                        .requestMatchers("/orders/my-orders/**")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/orders")
                        .hasAnyRole("USER", "ADMIN")

                        .requestMatchers(HttpMethod.PUT,
                                "/orders/cancel/**")
                        .hasAnyRole("USER","ADMIN")
                        // USERS ADMIN ONLY
                        .requestMatchers(HttpMethod.GET, "/users/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/users/**")
                        .hasRole("ADMIN")

                        // ORDERS ADMIN ONLY
                        .requestMatchers(HttpMethod.GET, "/orders")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/orders/*")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/orders/*/status")
                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/shipments/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/shipments/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/products/*/restock"
                        ).hasRole("ADMIN")

                        .requestMatchers("/drivers/**")
                        .hasRole("ADMIN")

                        // ADMIN MODULE
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        // USER MODULE
                        .requestMatchers("/user/**")
                        .hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}