package com.learing.springboot3.examples.repo;

import com.learing.springboot3.examples.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    List<VideoEntity> findByName(String name);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(
            String name, String description
    );

    List<VideoEntity> findByNameContainsIgnoreCase(String name);

    List<VideoEntity> findByDescriptionIgnoreCase(String description);

}
