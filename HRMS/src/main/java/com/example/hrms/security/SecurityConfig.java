package com.example.hrms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Kích hoạt @PreAuthorize và các annotation bảo mật khác
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/bookings/**",
                                "/meeting-room/**",
                                "/api/v1/users/all",
                                "/api/v1/users/check-username",
                                "/api/v1/users/create-account",
                                "/api/v1/users/departments-and-roles").permitAll()
                        .requestMatchers("/api/v1/users/update-account/**").hasAnyAuthority("Admin", "Supervisor")
                        .requestMatchers("/api/v1/users/delete/**")
                        .hasAuthority("ADMIN") // Chỉ Admin mới được xóa người dùng

                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Thay vì httpBasic(), dùng httpBasic(withDefaults())

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}