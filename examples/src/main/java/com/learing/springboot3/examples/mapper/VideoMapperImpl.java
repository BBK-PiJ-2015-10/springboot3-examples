package com.learing.springboot3.examples.mapper;

import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.entity.VideoEntity;
import org.springframework.stereotype.Component;

@Component
public class VideoMapperImpl implements VideoMapper {

    @Override
    public VideoEntity toVideoEntity(Video video, String description) {
        var videoEntity = new VideoEntity(video.name(), description);
        return videoEntity;
    }

    @Override
    public Video toVideo(VideoEntity videoEntity) {
        var video = new Video(videoEntity.getName());
        return video;
    }
}
