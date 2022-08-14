package com.dssc.pcube3.entityservice.entity;

import lombok.Data;

@Data
public class EsArticleCondition {

    private String id;

    private String title;

    private String content;

    private String keywords;

    private String theme;

    public static final String DOCUMENT_FILED_TITLE = "title";

    public static final String DOCUMENT_FILED_CONTENT = "content";

    public static final String DOCUMENT_FILED_KEYWORDS = "keywords";

    public static final String DOCUMENT_FILED_THEME = "theme";
}
