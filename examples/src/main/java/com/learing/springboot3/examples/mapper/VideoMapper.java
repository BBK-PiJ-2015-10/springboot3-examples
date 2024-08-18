package com.learing.springboot3.examples.mapper;

import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.entity.VideoEntity;

public interface VideoMapper {

    VideoEntity toVideoEntity(Video video, String description);

    Video toVideo(VideoEntity videoEntity);
}
