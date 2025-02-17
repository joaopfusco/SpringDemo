package com.example.demo.Service.Interfaces;

import com.example.demo.Domain.DTOs.LoginPayload;
import com.example.demo.Domain.DTOs.LoginResponse;
import com.example.demo.Domain.Models.User;

public interface IUserService extends IBaseService<User> {
    LoginResponse authenticate(LoginPayload payload);
}
