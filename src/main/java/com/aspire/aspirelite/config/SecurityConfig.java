package com.aspire.aspirelite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
        UserDetails prakhar = User.withUsername("aspire_admin")
                .password(passwordEncoder.encode("abc123"))
                .roles("ADMIN")
                .build();

        UserDetails pragya = User.withUsername("aspire_user1")
                .password(passwordEncoder.encode("abc123"))
                .roles("USER")
                .build();

        UserDetails usha = User.withUsername("aspire_user2")
                .password(passwordEncoder.encode("abc123"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(prakhar,pragya,usha);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.headers().frameOptions().disable();

        http.httpBasic().and().csrf().disable().cors().disable().authorizeHttpRequests(auth -> auth
                .requestMatchers("**/admin/**").hasRole("ADMIN")
                .requestMatchers("**/customer/**").hasRole("USER")
                .anyRequest().authenticated()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
