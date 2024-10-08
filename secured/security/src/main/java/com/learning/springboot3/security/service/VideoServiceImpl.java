package com.learning.springboot3.security.service;

import com.learning.springboot3.security.dto.NewVideo;
import com.learning.springboot3.security.dto.Search;
import com.learning.springboot3.security.dto.Video;
import com.learning.springboot3.security.entity.VideoEntity;
import com.learning.springboot3.security.mapper.VideoMapper;
import com.learning.springboot3.security.repo.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private VideoRepository videoRepository;

    private VideoMapper videoMapper;

    public VideoServiceImpl(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll().stream().map(videoMapper::toVideo).collect(Collectors.toList());
    }

    @Override
    public List<Video> search(Search search) {
        // This is using probes, could have done by findByNameContainsOrDescriptionContainsAllIgnoreCase
        VideoEntity probe = new VideoEntity();
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        Example<VideoEntity> example = Example.of(probe,
                ExampleMatcher.matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        );
        return videoRepository.findAll(example).stream().map(videoMapper::toVideo).toList();
    }

    @Override
    public Video create(NewVideo newVideo) {
        var videoEntity = new VideoEntity(newVideo.name(), newVideo.description());
        return videoMapper.toVideo(videoRepository.save(videoEntity));
    }

    @Override
    public void delete(Long videoId) {
        logger.info("Someone wants to delete video {}",videoId);
        videoRepository.deleteById(videoId);
    }

    @PostConstruct
    void initDatabase() {
        videoRepository.save(new VideoEntity("Need HELP with your SPRING BOOT 3 App?",
                "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."));
        videoRepository.save(new VideoEntity("Don't do THIS to your own CODE!",
                "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"));
        videoRepository.save(new VideoEntity("SECRETS to fix BROKEN CODE!",
                "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
    }
}
