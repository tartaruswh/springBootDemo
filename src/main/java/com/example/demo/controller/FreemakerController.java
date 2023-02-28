package com.example.demo.controller;

import com.example.demo.config.WXConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("freemaker")
public class FreemakerController {
    @Autowired
    private WXConfig wxConfig;
    @GetMapping("test")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("setting",wxConfig);
        //返回的页面地址
        return "fm/user/index";
    }
}
