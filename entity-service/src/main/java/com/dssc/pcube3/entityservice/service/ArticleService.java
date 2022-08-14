package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.EsArticleClient;
import com.dssc.pcube3.entityservice.dao.EventToArticleRelationDao;
import com.dssc.pcube3.entityservice.entity.Article;
import com.dssc.pcube3.entityservice.entity.EsArticleCondition;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ArticleService {

    private static final Integer FIND_NUMBER = 100;

    @Autowired
    private EsArticleClient esArticleClient;

    @Autowired
    private EventToArticleRelationDao eventToArticleRelationDao;

    public Article queryById(String id) {
        return esArticleClient.queryById(id);
    }

    public List<Article> query(EsArticleCondition esArticleCondition) {
        return esArticleClient.query(esArticleCondition, FIND_NUMBER);
    }

    public long countData() {
        return esArticleClient.countData();
    }

    public List<Article> getByEventId(String eventId) {
        String[] aids = eventToArticleRelationDao.findAidsByEventId(eventId);
        if (aids == null || aids.length == 0) {
            log.info("no articles related to eventId:{}", eventId);
            return new ArrayList<>();
        }

        return esArticleClient.queryByIds(Arrays.asList(aids));
    }

    public List<Article> getLatest(String keyword, Integer size) {
        EsArticleCondition esArticleCondition = new EsArticleCondition();
        esArticleCondition.setKeywords(keyword);
        return esArticleClient.query(esArticleCondition, FIND_NUMBER).stream()
                .sorted((a1, a2) -> a2.getTime().compareTo(a1.getTime()))
                .limit(size)
                .collect(Collectors.toList());
    }
}
