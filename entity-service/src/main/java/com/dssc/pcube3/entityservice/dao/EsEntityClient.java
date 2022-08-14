package com.dssc.pcube3.entityservice.dao;

import com.alibaba.fastjson.JSONObject;
import com.dssc.pcube3.entityservice.Utils.EsClientUtils;
import com.dssc.pcube3.entityservice.entity.Celebrity;
import com.dssc.pcube3.entityservice.entity.Entity;
import com.dssc.pcube3.entityservice.entity.EsEntityCondition;
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
public class EsEntityClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static final String INDEX_NAME = "entity";

    public Celebrity queryCelebrityById(String id) {
        GetRequest getRequest = new GetRequest(INDEX_NAME).id(id);
        Celebrity celebrity = null;
        try {
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            celebrity = JSONObject.parseObject(JSONObject.toJSONString(response.getSource()), Celebrity.class);
        } catch (IOException e) {
            log.warn("can't find celebrity, id:{}", id, e);
        }
        return celebrity;
    }

    public Entity queryEntityById(String id) {
        GetRequest getRequest = new GetRequest(INDEX_NAME).id(id);
        Entity entity = null;
        try {
            GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            entity = JSONObject.parseObject(JSONObject.toJSONString(response.getSource()), Entity.class);
        } catch (IOException e) {
            log.warn("can't find entity, id:{}", id, e);
        }
        return entity;
    }

    public List<Celebrity> queryCelebrity(EsEntityCondition esEntityCondition) {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.must(QueryBuilders.matchQuery(EsEntityCondition.DOCUMENT_FILED_ALIAS, esEntityCondition.getName()));
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(20);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder).indices(INDEX_NAME);

        List<Celebrity> celebrities = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            celebrities = EsClientUtils.convertAndSortByScore(hits).stream()
                    .map(obj -> JSONObject.parseObject(JSONObject.toJSONString(obj), Celebrity.class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.warn("search celebrities failed. condition:{}", JSONObject.toJSONString(esEntityCondition), e);
        }
        return celebrities;
    }

    public List<Entity> queryEntity(EsEntityCondition esEntityCondition) {
        BoolQueryBuilder queryBuilder = new BoolQueryBuilder();
        queryBuilder.filter(QueryBuilders.matchPhraseQuery(EsEntityCondition.DOCUMENT_FILED_NAME, esEntityCondition.getName()));
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(20);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder).indices(INDEX_NAME);

        List<Entity> entities = new ArrayList<>();
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            entities = EsClientUtils.convertAndSortByScore(hits).stream()
                    .map(obj -> JSONObject.parseObject(JSONObject.toJSONString(obj), Entity.class))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.warn("search entities failed. condition:{}", JSONObject.toJSONString(esEntityCondition), e);
        }
        return entities;
    }

    public long countData() {
        CountRequest countRequest = new CountRequest(INDEX_NAME);
        countRequest.query(QueryBuilders.matchAllQuery());
        try {
            CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
            return countResponse.getCount();
        } catch (IOException e) {
            log.warn("entity count failed.", e);
        }
        return 0;
    }
}
