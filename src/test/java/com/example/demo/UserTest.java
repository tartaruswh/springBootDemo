package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.utils.JsonData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)   //底层用junit，SpringJunitClassRunner
@SpringBootTest(classes={SpringDemo2Application.class})  //启动整个springboot工具
public class UserTest {

    @Autowired
    UserController userController;
    @Test
    public void loginTest() {
        User user = new User();
        user.setUsername("用户1");
        user.setPwd("mima1");
        JsonData jsonData = userController.login(user);
        System.out.println(jsonData.toString());
        Assert.assertEquals(0,jsonData.getCode());
    }
}
