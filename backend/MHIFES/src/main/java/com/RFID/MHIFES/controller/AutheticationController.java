package com.RFID.MHIFES.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RFID.MHIFES.config.TokenService;
import com.RFID.MHIFES.dto.AutheticationDTO;
import com.RFID.MHIFES.dto.LoginResponseDTO;
import com.RFID.MHIFES.dto.RegisterDTO;
import com.RFID.MHIFES.enums.UserRole;
import com.RFID.MHIFES.model.Usuario;
import com.RFID.MHIFES.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AutheticationController {

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository repository;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutheticationDTO data)
            throws IllegalArgumentException, UnsupportedEncodingException {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        Usuario user = (Usuario) auth.getPrincipal();

        return ResponseEntity.ok(new LoginResponseDTO(token, user.getLogin(), user.getNome(), user.getRole()));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        try {
            if (this.repository.findByLogin(data.login()) != null)
                return ResponseEntity.badRequest().build();

            String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

            Usuario newUser = new Usuario(data.login(), data.nome(), encryptedPassword, data.role());

            this.repository.save(newUser);

            return ResponseEntity.ok(data);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Error: Login já existente!");
        }
    }

    @PostConstruct
    public void initUserAdminAndToken() throws Exception {
        RegisterDTO registerAdmin = new RegisterDTO(
                "admin@2024",
                "Admin",
                "123456",
                UserRole.ADMIN);

        register(registerAdmin);
    }

}