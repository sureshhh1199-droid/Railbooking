package com.example.RailReservation.Security;

import com.example.RailReservation.Utill.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class
SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers("/api/auth/**").hasAnyRole("AGENT","ADMIN")
                        .requestMatchers("/api/user/**").hasAnyRole("ADMIN","AGENT")
                        .requestMatchers("/api/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/bookings/**").hasAnyRole("AGENT","ADMIN")
                        .requestMatchers("/api/cancel/**").hasAnyRole("AGENT","ADMIN")
                        .requestMatchers("/api/segments/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/notify/**").hasAnyRole("ADMIN")
                        .requestMatchers("/api/passengers/**").hasAnyRole("AGENT","ADMIN")
                        .requestMatchers("/api/pnr/**").hasAnyRole("AGENT","ADMIN")
                        .requestMatchers("/api/reports/**").hasAnyRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
