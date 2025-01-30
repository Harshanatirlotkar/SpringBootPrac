package com.harsh.myrest.security;


import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        UserDetails admin = User.withUsername("harsh")
                .password(encoder.encode("pwd"))
                .roles("admin")
                .build();

        UserDetails user = User.withUsername("john")
                .password(encoder.encode("pwd"))
                .roles("user")
                .build();

        return new InMemoryUserDetailsManager(admin,user);

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/v1/welcome").permitAll()
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("/v1/**").authenticated()
//                .and().formLogin()
//                .and().build();

        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/v1/welcome").permitAll()
                        .requestMatchers("/v1/**").authenticated()
                )
                .formLogin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


}
