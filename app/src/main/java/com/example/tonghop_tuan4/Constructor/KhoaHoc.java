package com.example.tonghop_tuan4.Constructor;

import java.util.Map;

public class KhoaHoc {
    private int id;
    private String name;
    private String image;
    private String time;
    private String description;
    private String banner;
    private String content;
    private String title;


    public KhoaHoc(){
    }

    public KhoaHoc(int id, String name, String image, String time, String description, String banner, String content, String title) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.time = time;
        this.description = description;
        this.banner = banner;
        this.content = content;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

