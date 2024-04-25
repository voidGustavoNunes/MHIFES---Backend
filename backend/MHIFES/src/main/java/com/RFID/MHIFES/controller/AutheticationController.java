package com.rfid.mhifes.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfid.mhifes.config.TokenService;
import com.rfid.mhifes.dto.AutheticationDTO;
import com.rfid.mhifes.dto.LoginResponseDTO;
import com.rfid.mhifes.dto.RegisterDTO;
import com.rfid.mhifes.enums.UserRole;
import com.rfid.mhifes.model.Usuario;
import com.rfid.mhifes.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AutheticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutheticationDTO data) throws IllegalArgumentException, UnsupportedEncodingException{
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        System.out.println(token);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
    
    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data) {
        
        if(this.repository.findByLogin(data.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Usuario newUser = new Usuario(data.login(), data.nome(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok(data);
    }
    
    @PostConstruct
    public void initUserAdminAndToken () throws Exception {
        RegisterDTO registerAdmin = new RegisterDTO(
            "admin@2024",
            "Admin", 
            "123456", 
            UserRole.ADMIN);

        register(registerAdmin);
    }

    // @GetMapping("/forAdmin")
    // @PreAuthorize("hasRole('Admin')")
    // public String forAdmin() {
    //     return "This URL is only accessible to admin";
    // }

    // @GetMapping("/forUser")
    // @PreAuthorize("hasRole('User')")
    // public String forUser() {
    //     return "This URL is only accessible to the user";
    // }
    

}