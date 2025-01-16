package com.learing.springboot3.examples.service;

import com.learing.springboot3.examples.dto.UniversalSearch;
import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.dto.VideoSearch;
import com.learing.springboot3.examples.entity.VideoEntity;
import com.learing.springboot3.examples.mapper.VideoMapper;
import com.learing.springboot3.examples.repo.VideoRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    public VideoServiceImpl(VideoRepository videoRepository, VideoMapper videoMapper) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
    }

    @Override
    public List<Video> getVideos() {
        return videoRepository.findAll().stream().map(videoMapper::toVideo).collect(Collectors.toList());
    }

    @Override
    public Video createVideo(Video video) {
        var videoEntity = videoMapper.toVideoEntity(video, "unavailable");
        videoRepository.save(videoEntity);
        return video;
    }

    @Override
    public List<VideoEntity> search(VideoSearch search) {
        var nameProvided = StringUtils.hasText(search.name());
        var descriptionProvided = StringUtils.hasText(search.description());
        List<VideoEntity> result = List.of();

        if (nameProvided && descriptionProvided) {
            return videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase(search.name(), search.description());
        }
        if (nameProvided) {
            return videoRepository.findByNameContainsIgnoreCase(search.name());
        }
        if (descriptionProvided) {
//            Examples of sorting
//            Sort sort = Sort.by("name").ascending()
//                    .and(Sort.by("description").descending());
//            Sort.TypedSort<VideoEntity> typedSort = Sort.sort(VideoEntity.class);
//            Sort sortTyped = typedSort.by(VideoEntity::getName).ascending()
//                    .and(typedSort.by(VideoEntity::getDescription).descending());
//            videoRepository.findByDescriptionIgnoreCase(search.name(), sort);
//            videoRepository.findByDescriptionIgnoreCase(search.name(),sortTyped);
            return videoRepository.findByDescriptionIgnoreCase(search.description());
        }
        return result;
    }

    @Override
    public List<VideoEntity> search(UniversalSearch search) {
        VideoEntity probe = new VideoEntity();
        // this will exactly match the non-null fields
        // the null fields if set are ignored all together
        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }
        //Example exampleExactlyMatchingNonNullFields = Example.of(probe);
        var matchingAllIgnoreAll = ExampleMatcher
                // this will match any
                //.matchingAll()
                // matches any, ignores case, and uses partial matching
                .matchingAny().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example customMatcherExample = Example.of(probe, matchingAllIgnoreAll);
        return videoRepository.findAll(customMatcherExample);
    }

    @Override
    public List<VideoEntity> findByName(String name) {
        return videoRepository.findByNameCustomQueryPureSQL(name);
    }

    @PostConstruct
    void initDatabase() {
        var video1 = new VideoEntity("Need HELP with your SPRING BOOT 3 App?", "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data.");
        var video2 = new VideoEntity("Don't do THIS to your own CODE!", "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!");
        var video3 = new VideoEntity("SECRETS to fix BROKEN CODE!", "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer.");
        var saved = videoRepository.saveAll(List.of(video1, video2, video3));
        saved.forEach(v -> logger.info("Saved video {}", v));
    }
}
