package com.learning.springboot3.security.mapper;

import com.learning.springboot3.security.dto.Video;
import com.learning.springboot3.security.entity.VideoEntity;

public interface VideoMapper {

    VideoEntity toEntity(Video video);

    Video toVideo(VideoEntity entity);


}
