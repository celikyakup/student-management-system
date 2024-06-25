package com.example.StudentManagementSystem.config;

import com.example.StudentManagementSystem.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter authenticationFilter;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .authorizeHttpRequests(registry -> {
                    registry.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll();
                    registry.requestMatchers("/api/users/**").permitAll();
                    registry.requestMatchers(HttpMethod.POST,"/api/students/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.POST,"/api/exams/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.POST,"/api/attendances/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.PUT,"/api/exams/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.PUT,"/api/attendances/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.GET,"/api/attendances").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.GET,"/api/attendances/**").permitAll();
                    registry.requestMatchers(HttpMethod.GET,"/api/exams").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.GET,"/api/exams/**").permitAll();
                    registry.requestMatchers(HttpMethod.POST,"/api/lessons/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.PUT,"/api/lessons/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.requestMatchers(HttpMethod.GET,"/api/lessons/**").permitAll();
                    registry.requestMatchers("api/students/**").permitAll();
                    registry.requestMatchers("api/teachers/**").hasAnyAuthority("ADMIN","TEACHER");
                    registry.anyRequest().authenticated();

                })
                //.formLogin(AbstractAuthenticationFilterConfigurer:: permitAll)
                .httpBasic(Customizer.withDefaults())
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout-> logout
                        .logoutUrl("/api/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
                .build();
    }
}
