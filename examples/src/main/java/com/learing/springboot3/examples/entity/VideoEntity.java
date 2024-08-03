package com.learing.springboot3.examples.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class VideoEntity {

    private @Id @GeneratedValue Long id;
    private String name;
    private String description;

    public VideoEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = null;
    }

    public VideoEntity() {
        this.description = null;
        this.name = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
