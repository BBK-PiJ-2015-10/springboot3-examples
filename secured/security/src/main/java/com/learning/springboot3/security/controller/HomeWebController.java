package com.learning.springboot3.security.controller;


import com.learning.springboot3.security.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeWebController {

    private final VideoService videoService;

    public HomeWebController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("videos", videoService.getAllVideos());
        return "index";
    }
}
