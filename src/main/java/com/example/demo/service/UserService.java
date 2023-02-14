package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;

public interface UserService {
    String login(String username, String pwd);
    List<User> list();
}
