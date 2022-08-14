package com.dssc.pcube3.entityservice.entity;

import lombok.Data;

import java.util.List;

@Data
public class Article {

    private String id;

    private String url;

    private String time;

    private String title;

    private String theme;

    private List<String> keywords;

    private String content;

    private String image;
}
