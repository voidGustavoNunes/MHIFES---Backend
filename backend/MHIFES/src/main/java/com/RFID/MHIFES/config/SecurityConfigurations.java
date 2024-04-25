package com.rfid.mhifes.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.rfid.mhifes.repository.UserRepository;
import com.rfid.mhifes.service.AuthorizationService;

@Configuration
@EnableWebSecurity
// EU ADICIONEI
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;
    
    @Autowired
    private UserRepository repository;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                // EU ADICIONEI CORS
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedHeader("*");
                    config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
                    config.setAllowedOriginPatterns(List.of("http://localhost:4200"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                //  COMO LIDAR COM ERRO NO ACESSO?
                // .exceptionHandling((exceptionHandling) ->
                //     exceptionHandling
                //         .accessDeniedPage("/auth/access-denied"))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(HttpMethod.POST, "api/auth/login").permitAll() //permitir que qualquer pessoa consiga fazer login
                    .requestMatchers(HttpMethod.POST, "api/auth/register").permitAll() //permitir que qualquer pessoa consiga se cadastrar
                    
                    //  EU MUDEI
                    .requestMatchers("/api/**").hasRole("ADMIN")
                    // .requestMatchers(HttpMethod.POST, "/disciplina").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    )
                    .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                    
                .build(); //stateless
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
