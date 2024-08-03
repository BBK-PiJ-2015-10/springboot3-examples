package com.learing.springboot3.examples.controller;


import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VideoService videoService;

    public RestApiController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/api/v1/videos")
    public List<Video> getVideos() {
        logger.info("API received request to get all videos");
        return videoService.getVideos();
    }


    @PostMapping("/api/v1/videos")
    public Video createVideo(@RequestBody Video video) {
        logger.info("API received request to create video {}", video);
        return videoService.createVideo(video);
    }

}
