package com.example.demo.controller;

import com.example.demo.config.WXConfig;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("api/v1/test")
@RestController
@PropertySource("classpath:pay.properties")  //非类配置文件
public class TestController {
    @Value("${wxpay.appid}")
    private String payAppid;
    @Value("${wxpay.secret}")
    private String paySecret;

    @GetMapping("get_config")
    public JsonData testConfig() {
        Map<String, String> map = new HashMap<>();
        map.put("appid", payAppid);
        map.put("secret", paySecret);
        return JsonData.buildSuccess(map);
    }
    @Autowired
    private WXConfig wxConfig;
    @GetMapping("get_config_by_class")
    public JsonData testConfigByClass() {
        Map<String, String> map = new HashMap<>();
        map.put("appid", wxConfig.getPayAppid());
        map.put("secret", wxConfig.getPaySecret());
        map.put("mechId", wxConfig.getPayMechId());
        return JsonData.buildSuccess(map);
    }

    @GetMapping("list")
    public JsonData testExt() {
        int i = 1/0;
        return JsonData.buildSuccess("");
    }
}