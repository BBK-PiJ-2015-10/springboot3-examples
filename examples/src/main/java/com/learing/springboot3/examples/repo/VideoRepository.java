package com.learing.springboot3.examples.repo;

import com.learing.springboot3.examples.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    List<VideoEntity> findByName(String name);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(
            String name, String description
    );

    List<VideoEntity> findByNameContainsIgnoreCase(String name);

    List<VideoEntity> findByDescriptionIgnoreCase(String description);

    @Query(value = "select * from VIDEO_ENTITY where NAME = ?1",nativeQuery = true)
    List<VideoEntity> findByNameCustomQueryPureSQL(String name);

}
