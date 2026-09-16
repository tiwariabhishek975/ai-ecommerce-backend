package com.example.aiecommerce.service;

import com.example.aiecommerce.dto.*;
import com.example.aiecommerce.entity.*;
import com.example.aiecommerce.exception.BadRequestException;
import com.example.aiecommerce.repository.UserRepository;
import com.example.aiecommerce.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository repo; private final PasswordEncoder encoder; private final JwtService jwt;
    public AuthService(UserRepository repo,PasswordEncoder encoder,JwtService jwt){this.repo=repo;this.encoder=encoder;this.jwt=jwt;}
    public AuthResponse register(RegisterRequest r){
        if(repo.existsByEmail(r.email())) throw new BadRequestException("Email already registered");
        User u=new User(r.name(),r.email(),encoder.encode(r.password()),Role.USER); repo.save(u);
        return new AuthResponse(jwt.generateToken(u.getEmail()),u.getId(),u.getName(),u.getEmail(),u.getRole().name());
    }
    public AuthResponse login(LoginRequest r){
        User u=repo.findByEmail(r.email()).orElseThrow(()->new BadRequestException("Invalid email or password"));
        if(!encoder.matches(r.password(),u.getPassword())) throw new BadRequestException("Invalid email or password");
        return new AuthResponse(jwt.generateToken(u.getEmail()),u.getId(),u.getName(),u.getEmail(),u.getRole().name());
    }
}
