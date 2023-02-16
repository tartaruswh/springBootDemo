package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    public static Map<String, User> sessionMap = new HashMap<>();
    @Autowired
    UserMapper userMapper;

    /**
     * 登录数据与数据库中一致，则生成token，并与对应的用户对象存储到map集合中
     * @param username
     * @param pwd
     * @return
     */
    @Override
    public String login(String username, String pwd) {
        User user = userMapper.login(username, pwd);
        if (user == null) {
            return null;
        }else {
            String token = UUID.randomUUID().toString();
            sessionMap.put(token,user);
            return token;
        }
    }

    @Override
    public List<User> list() {
        return userMapper.list();
    }
}
