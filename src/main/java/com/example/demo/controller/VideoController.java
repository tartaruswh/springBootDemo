package com.example.demo.controller;

import com.example.demo.mapper.VideoMapper;
import com.example.demo.model.Video;
import com.example.demo.service.VideoService;
import com.example.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 视频控制器
 */
//@Controller   不会返回json格式数据,无法返回字符串，虽然返回字符串不规范
@RestController  //会返回json格式数据，也可返回字符串，虽然返回字符串不规范
@RequestMapping("api/v1/pub/video")  //接口请求地址共用url
public class VideoController {
    @Autowired
    VideoService videoService;

    @RequestMapping("list")
    public Object list() {
//        Map<String,String> map = new HashMap<>();
//        map.put("1","面试专题课程");
//        map.put("2","SpringBoot微服务课程");
//        return map;
        List<Video> list = new ArrayList<>();
        list = videoService.listVideo();
//        设置业务执行状态码
        return JsonData.buildSuccess(list);
    }

    @PostMapping("save_video_chapter")
    public JsonData saveVideoChapter(@RequestBody Video video) {
        System.out.println(video.toString());
        return JsonData.buildSuccess("");

    }
}
