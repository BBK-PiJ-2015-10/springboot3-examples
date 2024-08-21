package com.learning.springboot3.security.service;

import com.learning.springboot3.security.dto.NewVideo;
import com.learning.springboot3.security.dto.Search;
import com.learning.springboot3.security.dto.Video;
import com.learning.springboot3.security.entity.VideoEntity;

import java.util.List;

public interface VideoService {

    List<Video> getAllVideos();

    List<Video> search(Search search);

    Video create(NewVideo newVideo);

    void delete(Long videoId);

}
