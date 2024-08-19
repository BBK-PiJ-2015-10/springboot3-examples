package com.learning.springboot3.security.service;

import com.learning.springboot3.security.dto.NewVideo;
import com.learning.springboot3.security.dto.Search;
import com.learning.springboot3.security.entity.VideoEntity;

import java.util.List;

public interface VideoService {

    List<VideoEntity> getAllVideos();

    List<VideoEntity> search(Search search);

    VideoEntity create(NewVideo newVideo);

    void delete(Long videoId);

}
