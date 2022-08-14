package com.dssc.pcube3.entityservice.dao;

import com.alibaba.fastjson.JSONObject;
import com.dssc.pcube3.entityservice.Utils.EsClientUtils;
import com.dssc.pcube3.entityservice.entity.Event;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.IdsQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Repository
@Log4j2
public class EsEventClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static final String INDEX_NAME = "event_summary";

    public Event queryById(String id) {
        GetRequest getRequest = new GetRequest(INDEX_NAME).id(id);
        Event event = null;
        try {
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            event = JSONObject.parseObject(JSONObject.toJSONString(response.getSource()), Event.class);
        } catch (IOException e) {
            log.warn("can't find event, id:{}", id, e);
        }
        return event;
    }

    public List<Event> queryByIds(List<String> ids) {
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        idsQueryBuilder.ids().addAll(ids);

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(idsQueryBuilder).size(ids.size());
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder).indices(INDEX_NAME);
        return restQuery(searchRequest);
    }

    public List<Event> queryByKeyword(String keyword, Integer size) {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.filter(QueryBuilders.matchPhraseQuery("abstract", keyword));
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(size);
        SearchRequest searchRequest = new SearchRequest().indices(INDEX_NAME).source(searchSourceBuilder);

        return restQuery(searchRequest);
    }

    public long countData() {
        CountRequest countRequest = new CountRequest(INDEX_NAME);
        countRequest.query(QueryBuilders.matchAllQuery());

        try {
            CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
            return countResponse.getCount();
        } catch (IOException e) {
            log.warn("event count failed", e);
        }
        return 0;
    }

    private List<Event> restQuery(SearchRequest searchRequest) {
        List<Event> events = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            events = EsClientUtils.convertAndSortByScore(hits).stream()
                    .map(obj -> JSONObject.parseObject(JSONObject.toJSONString(obj), Event.class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.warn("search events failed. searchRequest:{}", searchRequest.toString(), e);
        }
        return events;
    }
}
