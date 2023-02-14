package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//pub表示没有权限限制，pri则有
@RestController
@RequestMapping("api/v1/pub/user")
public class UserController {
    /**
     * 直接传请求参数
     * @param username
     * @param pwd
     * @return
     */
//    @PostMapping("login")
//    public JsonData login(String username,String pwd) {
//        User user = new User(0,username, pwd);
//        System.out.println("username：" + username + "pwd：" + pwd);
//        return JsonData.buildSuccess(user);
//    }
    @Autowired
    public UserService userService;
    /**
     * 直接传类对象
     * @param user
     * @return
     */
    @PostMapping("login")
    public JsonData login(@RequestBody User user) {
        System.out.println("user：" + user.toString());
        String token = userService.login(user.getUsername(), user.getPwd());
        return token != null ? JsonData.buildSuccess(token):JsonData.buidlError("账号密码错误");
    }

    @GetMapping("list")
    public JsonData list() {
        return JsonData.buildSuccess(userService.list());
    }
}
