package com.example.demo.Service.Services;

import com.example.demo.Domain.DTOs.LoginPayload;
import com.example.demo.Domain.DTOs.LoginResponse;
import com.example.demo.Domain.Models.User;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Service.Interfaces.IUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService extends BaseService<User> implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void insert(User model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        super.insert(model);
    }

    @Override
    public void update(User model) {
        model.setPassword(passwordEncoder.encode(model.getPassword()));
        super.update(model);
    }

    @Override
    public LoginResponse authenticate(LoginPayload payload) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(payload.getUsername()));

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Usuário não existe!");
        }

        User user = optionalUser.get();

        if (!passwordEncoder.matches(payload.getPassword(), user.getPassword())) {
            throw new RuntimeException("Usuário ou senha incorretos!");
        }

        try {
            String token = jwtService.generateToken(user);
            return new LoginResponse(token, user);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
