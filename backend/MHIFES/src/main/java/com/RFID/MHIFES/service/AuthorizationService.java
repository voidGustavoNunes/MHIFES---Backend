package com.rfid.mhifes.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rfid.mhifes.repository.postgres.UsuarioRepository;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UsuarioRepository userRepository;

    public AuthorizationService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByLogin(username);
    }

}
