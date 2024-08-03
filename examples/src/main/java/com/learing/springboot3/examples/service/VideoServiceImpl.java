package com.learing.springboot3.examples.service;

import com.learing.springboot3.examples.dto.Video;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Video createVideo(Video video) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(video);
        this.videos = List.copyOf(extend);
        return video;
    }
}
