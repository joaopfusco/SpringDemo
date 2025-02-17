package com.example.demo.Repository;

import com.example.demo.Domain.Models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IBaseRepository<User> {
    User findByUsername(String username);
}
