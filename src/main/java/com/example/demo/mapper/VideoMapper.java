package com.example.demo.mapper;

import com.example.demo.model.Video;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository //自动声明该类的对象，供程序调用
public class VideoMapper implements Serializable {
    /**
     * 模拟获取数据库数据
     */
    private static Map<Integer, Video> videoMapper = new HashMap<>();
    static {
        videoMapper.put(1, new Video(1, "第一章"));
        videoMapper.put(2, new Video(2, "第二章"));
        videoMapper.put(3, new Video(3, "第三章"));
        videoMapper.put(4, new Video(4, "第四章"));
        videoMapper.put(5, new Video(5, "第五章"));
    }

    //    将模拟的数据库中的数据获取后赋值到集合
    public List<Video> listVideo() {
        List<Video> list = new ArrayList<>();
        list.addAll(videoMapper.values());
        return list;
    }
}
