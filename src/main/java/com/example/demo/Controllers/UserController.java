package com.example.demo.Controllers;

import com.example.demo.Domain.DTOs.LoginPayload;
import com.example.demo.Domain.DTOs.LoginResponse;
import com.example.demo.Domain.Models.User;
import com.example.demo.Service.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User> {

    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload payload) {
        LoginResponse response = service.authenticate(payload);
        return ResponseEntity.ok(response);
    }
}
