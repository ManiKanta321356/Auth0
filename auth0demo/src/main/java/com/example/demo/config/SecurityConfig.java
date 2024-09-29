package com.example.demo.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(customizer -> customizer.disable()) // Disable CSRF protection for OAuth2 login
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home", "/products", "/categories").permitAll() // Public paths
                        .requestMatchers("/cart", "/orders", "/login","/register", "/profile").authenticated() // Protected paths
                        .anyRequest().permitAll() // Permit any other request
                )
                .oauth2Login(login -> login
                        .loginPage("/oauth2/authorization/auth0") // Specify the OAuth2 login page
                        .defaultSuccessUrl("/home1", true)) // Redirect here after successful login
                .logout(logout -> logout
                        .logoutSuccessHandler(oidcLogoutSuccessHandler()) // Custom Auth0 logout handler
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .deleteCookies("JSESSIONID") // Remove session cookie
                        .logoutSuccessUrl("/home2")) // Redirect to home2 after logout
                		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .build();
    }

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            String logoutUrl = "https://dev-m0pu2uuglyzgrxx5.us.auth0.com/v2/logout?client_id=R2cFlMGSpRXmdCaeui4d0xZgdafgeMLm&returnTo=http://localhost:8085/home2";
            response.sendRedirect(logoutUrl); // Redirect to Auth0 logout and then back to home2
        };
    }
}
