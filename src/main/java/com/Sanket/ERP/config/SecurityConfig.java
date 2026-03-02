//package com.Sanket.ERP.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.Customizer;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Disabled for testing/Postman
//                .authorizeHttpRequests(auth -> auth
//                        // Rule 1: Anyone can see the login page or public info
//                        .requestMatchers("/api/public/**", "/", "/home", "/error").permitAll()
//
//                        // Rule 2: Only authorized users can see Project details
//                        .requestMatchers("/api/projects/**").hasAnyRole("ADMIN", "MANAGER")
//
//                        // Rule 3: Only authorized users can see Accessory info
//                        .requestMatchers("/api/accessories/**").authenticated()
//
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults()); // Uses Basic Auth (Username/Password)
//
//        return http.build();
//    }
//}

package com.Sanket.ERP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf-cross server request forgery is a security vulnerability where attackers trick the users to perform an unintended actions on a web application
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        return http.build();
    }
}