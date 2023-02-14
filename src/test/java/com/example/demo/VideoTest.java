package com.example.demo;

import com.example.demo.model.Video;
import com.example.demo.service.VideoService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)   //底层用junit，SpringJunitClassRunner
@SpringBootTest(classes={SpringDemo2Application.class})  //启动整个springboot工具
@AutoConfigureMockMvc
public class VideoTest {
    @Autowired
    VideoService videoService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testVideoListApi() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/pub/video/list"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        System.out.println(mvcResult.getResponse().getStatus());
    }
    @Before
    public void testOne() {
        System.out.println("这个是测试 before");
    }

    @Test
    public void testVideoList() {
        List<Video> videoList = videoService.listVideo();
        TestCase.assertTrue(videoList.size()>0);

    }
    @Test
    public void testTwo() {
        System.out.println("这个是测试 test");
    }

    @Test
    public void testTwo2() {
        System.out.println("这个是测试 test");
    }

    @After
    public void testThree() {
        System.out.println("这个是测试 after");
    }
}
