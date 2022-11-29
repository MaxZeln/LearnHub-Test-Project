package ru.learnhub.learnhubtestproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import ru.learnhub.learnhubtestproject.security.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthService personDetailsService;
    private final AuthenticationConfiguration configuration;

    @Autowired
    public SecurityConfig(AuthService personDetailsService,
                          AuthenticationConfiguration configuration) {
        this.personDetailsService = personDetailsService;
        this.configuration = configuration;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http

            .csrf(csrf ->  csrf.disable())

            .authorizeHttpRequests(auth -> {
                auth
                        .requestMatchers( "/auth/registration", "/auth/login", "/error").permitAll()

                        .anyRequest().authenticated();

            })

            .formLogin(formLogin -> {
                formLogin.loginPage("/auth/login");
                formLogin.loginProcessingUrl("/login");
                formLogin.defaultSuccessUrl("/auth/login", true);
                formLogin.failureUrl("/auth/login?error");
            })

            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/html/login")

            .and()

            .httpBasic(Customizer.withDefaults())

            .build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Autowired
    void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
