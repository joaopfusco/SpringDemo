package com.example.demo.Service.Interfaces;

import com.example.demo.Domain.Models.User;

public interface IJwtService {
    String generateToken(User user);
}
