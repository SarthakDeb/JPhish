package com.example.jphish.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

        @Bean
        public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
                throws Exception {
            http
                    .authorizeHttpRequests((authorize) -> authorize
                            .anyRequest().permitAll()
                    )
                    // Form login handles the redirect to the login page from the
                    // authorization server filter chain
                    .formLogin(Customizer.withDefaults())
                    .csrf().disable()
                    .cors().disable();

            return http.build();
        }
    }






