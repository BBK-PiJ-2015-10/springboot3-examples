package com.learing.springboot3.examples.repo;

import com.learing.springboot3.examples.entity.VideoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    // Exact match for name
    List<VideoEntity> findByName(String name);

    // This will fetch all videos where name is null
    List<VideoEntity> findByNameIsNull();

    // Contains provides partially matches and ignore case ignores case
    // OR is a simple OR
    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(
            String name, String description
    );

    // Contains provides partially matches and ignore case ignores case
    List<VideoEntity> findByNameContainsIgnoreCase(String name);

    // Exact match for description but ignores case
    List<VideoEntity> findByDescriptionIgnoreCase(String description);

    // Contains provides partially matches and ignore case ignores case and sorts by name ascending
    List<VideoEntity> findByDescriptionIgnoreCaseOrderByNameAsc(String description);

    // Need to pass the Sort as a parameter
    List<VideoEntity> findByDescriptionIgnoreCase(String description, Sort sort);

    // Native query means SQL query vs JPQL
    @Query(value = "select * from VIDEO_ENTITY where NAME = ?1",nativeQuery = true)
    List<VideoEntity> findByNameCustomQueryPureSQL(String name);

}
