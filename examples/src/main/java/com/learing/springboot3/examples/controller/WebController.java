package com.learing.springboot3.examples.controller;

import com.learing.springboot3.examples.dto.UniversalSearch;
import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.dto.VideoSearch;
import com.learing.springboot3.examples.service.VideoService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


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

    @PostMapping("/new-video")
    public String createVideo(@ModelAttribute Video video) {
        logger.info("Received request to create video {}", video);
        videoService.createVideo(video);
        return "redirect:/";
    }


    @PostMapping("/multi-field-search")
    public String multiFieldSearch(@ModelAttribute VideoSearch search,
                                   Model model) {
        logger.info("Received multiFields search with {}", search);
        var videos = videoService.search(search);
        model.addAttribute("videos", videos);
        return "index";
    }

    @PostMapping("/universal-search")
    public String universalSearch(@ModelAttribute UniversalSearch search, Model model) {
        logger.info("Received universal search with {}", search);
        var videos = videoService.search(search);
        model.addAttribute("videos", videos);
        return "index";
    }


}
