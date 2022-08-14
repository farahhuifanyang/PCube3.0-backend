package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.ArticleToEventRelationDao;
import com.dssc.pcube3.entityservice.dao.EsArticleClient;
import com.dssc.pcube3.entityservice.dao.EsEventClient;
import com.dssc.pcube3.entityservice.dao.EventToArticleRelationDao;
import com.dssc.pcube3.entityservice.entity.Article;
import com.dssc.pcube3.entityservice.entity.Event;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class EventService {

    private static final Integer FIND_NUMBER = 100;

    @Autowired
    private EsEventClient esEventClient;
    @Autowired
    private EntityService entityService;
    @Autowired
    private ArticleToEventRelationDao articleToEventRelationDao;
    @Autowired
    private EventToArticleRelationDao eventToArticleRelationDao;
    @Autowired
    private EsArticleClient esArticleClient;

    public Event queryById(String id) {
        return esEventClient.queryById(id);
    }

    public List<Event> queryByKeyword(String keyword) {
        return esEventClient.queryByKeyword(keyword, FIND_NUMBER);
    }

    public List<Event> queryLatest(String keyword, Integer size) {
        return esEventClient.queryByKeyword(keyword, FIND_NUMBER).stream()
                .sorted((e1, e2) -> e2.getEid().compareTo(e1.getEid()))
                .limit(size)
                .peek(this::setRelatedImg)
                .collect(Collectors.toList());
    }

    public List<Event> queryByEntityId(String entityId) {
        List<String> aids = entityService.getAidsByEid(entityId);
        if (CollectionUtils.isEmpty(aids)) {
            return new ArrayList<>();
        }

        Set<String> eventIds;
        try {
            eventIds = articleToEventRelationDao.getEventIdsByAids(aids)
                    .stream().filter(StringUtils::isNotBlank)
                    .collect(Collectors.toSet());
        } catch (IOException e) {
            log.warn("get eventId by aids failed. aids:{}", aids, e);
            return new ArrayList<>();
        }

        if (CollectionUtils.isEmpty(eventIds)) {
            return new ArrayList<>();
        }

        // 按照时间倒序排序
        return esEventClient.queryByIds(new ArrayList<>(eventIds))
                .stream().sorted((e1, e2) -> e2.getEid().compareTo(e1.getEid()))
                .collect(Collectors.toList());
    }

    private void setRelatedImg(Event event) {
        String[] aids = eventToArticleRelationDao.findAidsByEventId(event.getEid());
        List<String> imgList = esArticleClient.queryByIds(Arrays.asList(aids))
                .stream().map(Article::getImage).filter(Strings::isNotBlank).collect(Collectors.toList());
        event.setRelatedImgList(imgList);
    }

    public long countData() {
        return esEventClient.countData();
    }
}
