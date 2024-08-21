package com.learning.springboot3.security.controller;


import com.learning.springboot3.security.dto.NewVideo;
import com.learning.springboot3.security.dto.Search;
import com.learning.springboot3.security.service.VideoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/new-video")
    public String newVideo(@ModelAttribute NewVideo newVideo) {
        videoService.create(newVideo);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String universalSearch(@ModelAttribute Search search, Model model) {
        var searchResults = videoService.search(search);
        model.addAttribute("search", search);
        model.addAttribute("videos", searchResults);
        return "index";
    }

    @PostMapping("/delete/videos/{videoId}")
    public String deleteVideo(@PathVariable Long videoId) {
        videoService.delete(videoId);
        return "redirect:/";
    }
    
}
