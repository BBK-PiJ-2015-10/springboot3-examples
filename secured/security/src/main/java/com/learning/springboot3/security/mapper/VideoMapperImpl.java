package com.learning.springboot3.security.mapper;

import com.learning.springboot3.security.dto.Video;
import com.learning.springboot3.security.entity.VideoEntity;
import org.springframework.stereotype.Component;

@Component
public class VideoMapperImpl implements VideoMapper {

    public VideoMapperImpl() {
    }

    @Override
    public VideoEntity toEntity(Video video) {
        VideoEntity entity = new VideoEntity(video.name(), video.description());
        if (video.id() != null) {
            entity.setId(video.id());
        }
        return entity;
    }

    @Override
    public Video toVideo(VideoEntity entity) {
        return new Video(entity.getName(), entity.getDescription(), entity.getId());
    }
}
