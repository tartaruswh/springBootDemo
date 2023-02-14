package com.example.demo.service;

import com.example.demo.model.Video;

import java.util.List;

/**
 * 通常用于微服务解耦使用
 */
public interface VideoService {
    public List<Video> listVideo();
}