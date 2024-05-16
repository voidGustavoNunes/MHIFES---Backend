package com.rfid.mhifes.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.addAllowedHeader("*");
                    config.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT"));
                    config.setAllowedOriginPatterns(List.of("http://localhost:4200"));
                    config.setAllowCredentials(true);
                    return config;
                }))
                .csrf(csrf -> csrf.disable())
                // COMO LIDAR COM ERRO NO ACESSO?
                // .exceptionHandling((exceptionHandling) ->
                // exceptionHandling
                // .accessDeniedPage("/auth/access-denied"))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        // Erro de autenticação (401). Ex: token inválido
                        .authenticationEntryPoint(
                                (request, response, authException) -> response.sendError(401, "Não autenticado"))
                        // Erro de autorização (403). Ex: usuário sem permissão
                        .accessDeniedHandler(
                                (request, response, accessDeniedException) -> response.sendError(403, "Acesso negado")))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "api/auth/login").permitAll() // permitir que qualquer pessoa
                                                                                        // consiga fazer login
                        .requestMatchers(HttpMethod.GET, "/api/home/**").permitAll() // permitir que qualquer pessoa consiga
                                                                                    // acessar a home
                        // .requestMatchers(HttpMethod.POST, "api/auth/register").permitAll() //
                        // permitir que qualquer
                        // pessoa consiga se
                        // cadastrar

                        .requestMatchers("/api/alocacoes").hasRole("USER")
                        .requestMatchers("/api/**").hasRole("ADMIN")
                        // .requestMatchers("/home").permitAll()
                        // .requestMatchers("/home").hasAnyRole("ADMIN", "USER") // Role ADMIN
                        // .requestMatchers(HttpMethod.POST, "/disciplina").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)

                .build(); // stateless
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
