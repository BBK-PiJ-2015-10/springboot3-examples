package com.learing.springboot3.examples.service;

import com.learing.springboot3.examples.dto.Video;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    List<Video> videos = List.of(
            new Video("Being the best in Java"),
            new Video("JVM expert")
    );

    @Override
    public List<Video> getVideos() {
        return videos;
    }
}
