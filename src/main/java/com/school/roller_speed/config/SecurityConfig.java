package com.school.roller_speed.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfig {

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public InMemoryUserDetailsManager userDetailsService(PasswordEncoder encoder) {
                UserDetails admin = User.withUsername("admin")
                                .password(encoder.encode("1234"))
                                .roles("ADMIN")
                                .build();

                UserDetails docente = User.withUsername("docente")
                                .password(encoder.encode("doc123"))
                                .roles("DOCENTE")
                                .build();

                UserDetails estudiante = User.withUsername("estudiante")
                                .password(encoder.encode("est123"))
                                .roles("ESTUDIANTE")
                                .build();

                return new InMemoryUserDetailsManager(admin, docente, estudiante);
        }

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
                                "/error"
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
