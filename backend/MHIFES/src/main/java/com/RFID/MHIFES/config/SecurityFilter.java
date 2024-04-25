package com.rfid.mhifes.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rfid.mhifes.repository.UsuarioRepository;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    TokenService tokenService;
    
    @Autowired
    UserDetailsService userDetailsService;
    
    @Autowired
    UsuarioRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // EU MUDEI
        String userName = this.recoverToken(request);
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            
            if(tokenService.isValidToken(userName, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            
        }
        filterChain.doFilter(request, response);
        // if(token != null){
        //     var login = tokenService.validateToken(token);
        //     UserDetails user = userRepository.findByLogin(login);

        //     var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        //     SecurityContextHolder.getContext().setAuthentication(authentication);
        // }
        // filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        // if(authHeader == null) return null;
        // return authHeader.replace("Bearer ", "");
        
        // EU MUDEI
        String jwtToken = null;
        String loginUser = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.replace("Bearer ", "");
            loginUser = tokenService.getUserNameFromToken(jwtToken);
        }
        return loginUser;
    }
}