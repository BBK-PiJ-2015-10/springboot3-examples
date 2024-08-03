package com.learing.springboot3.examples.service;

import com.learing.springboot3.examples.dto.UniversalSearch;
import com.learing.springboot3.examples.dto.Video;
import com.learing.springboot3.examples.dto.VideoSearch;
import com.learing.springboot3.examples.entity.VideoEntity;

import java.util.List;

public interface VideoService {

    List<Video> getVideos();

    Video createVideo(Video video);

    List<VideoEntity> search(VideoSearch search);

    List<VideoEntity> search(UniversalSearch search);

}
