package com.example.demo.mapper;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class UserMapper implements Serializable {
    /**
     * 模拟获取数据库数据
     */
    private static HashMap<String, User> userMapper = new HashMap<>();
    static {
        userMapper.put("用户1", new User(1, "用户1","mima1"));
        userMapper.put("用户2", new User(2, "用户2","mima2"));
        userMapper.put("用户3", new User(3, "用户3","mima3"));
        userMapper.put("wh", new User(4,"wh", "1234"));
    }

    /**
     * 判断登录请求的用户名与密码和保存的是否一致
     * @param username
     * @param pwd
     * @return
     */
    public User login(String username, String pwd) {
        User user = userMapper.get(username);
        if (user == null) {
            return null;
        }
        if (user.getPwd().equals(pwd)) {
            return user;
        }
        return null;
    }

    /**
     * 返回所有用户数据，以list集合的形式返回
     * @return
     */
    public List<User> list() {
        List<User> listUser = new ArrayList<>();
        listUser.addAll(userMapper.values());
        return listUser;
    }
}
