package com.learing.springboot3.examples.controller;

import com.learing.springboot3.examples.service.VideoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VideoService videoService;

    public WebController(VideoService videoService) {
        this.videoService = videoService;
    }


    @GetMapping("/")
    public String index(Model model) {
        logger.info("Received request to get all videos");
        var videos = videoService.getVideos();
        model.addAttribute("videos", videos);
        return "index";
    }


}
