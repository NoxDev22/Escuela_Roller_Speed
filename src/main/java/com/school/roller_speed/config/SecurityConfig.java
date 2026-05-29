package com.school.roller_speed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        // UserDetailsService is provided by DatabaseUserDetailsService (JPA). Keep PasswordEncoder here.

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                       AuthenticationSuccessHandler successHandler) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/css/**",
                                "/images/**",
                                "/",
                                "/login",
                                "/mision",
                                "/vision",
                                "/servicios",
                                "/valores",
                                "/eventos",
                                "/index",
                                "/error",
                                "/vista-de-estudiantes" // <-- Le damos permiso libre para ver tus datos reales
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
        }
}