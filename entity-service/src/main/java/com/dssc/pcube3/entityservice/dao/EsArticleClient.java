package com.dssc.pcube3.entityservice.dao;

import com.alibaba.fastjson.JSONObject;
import com.dssc.pcube3.entityservice.Utils.EsClientUtils;
import com.dssc.pcube3.entityservice.entity.Article;
import com.dssc.pcube3.entityservice.entity.EsArticleCondition;
import lombok.extern.log4j.Log4j2;
import org.apache.directory.api.util.Strings;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Log4j2
public class EsArticleClient {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    private static final String[] INDEX_NAMES = new String[]{"ltn", "udn", "ct"};

    public Article queryById(String id) {
        List<Article> result = queryByIds(Collections.singletonList(id));
        if (CollectionUtils.isEmpty(result)) {
            log.warn("can't find article by id:{}", id);
            return null;
        }
        return JSONObject.parseObject(JSONObject.toJSONString(result.get(0)), Article.class);
    }

    public List<Article> queryByIds(List<String> ids) {
        IdsQueryBuilder idsQueryBuilder = QueryBuilders.idsQuery();
        idsQueryBuilder.ids().addAll(ids);

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .query(idsQueryBuilder);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);

        // 只能这么设置多索引查询..
        searchRequest.indices("ltn","imaged_udn","imaged_ct");

        List<Article> articles = new ArrayList<>();
        try {
            articles = restQuery(searchRequest);
        } catch (IOException e) {
            log.warn("search by Id failed. ids:{}", ids.toString(), e);
        }
        return articles;
    }

    public List<Article> query(EsArticleCondition esArticleCondition, Integer size) {
        BoolQueryBuilder queryBuilder = buildFuzzQueryBuilder(esArticleCondition);
        // 暂时写死查100个
        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource().query(queryBuilder).size(size);
        SearchRequest searchRequest = new SearchRequest().source(searchSourceBuilder);
        // 设置查询范围
        searchRequest.indices("imaged_udn","imaged_ct","ltn");

        List<Article> articles = new ArrayList<>();
        try {
            articles = restQuery(searchRequest);
        } catch (IOException e) {
            log.warn("search failed. condition:{}", JSONObject.toJSONString(esArticleCondition), e);
        }
        return articles;
    }

    public long countData() {
        CountRequest countRequest = new CountRequest();
        countRequest.indices("imaged_udn","imaged_ct","ltn").query(QueryBuilders.matchAllQuery());

        try {
            CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
            return countResponse.getCount();
        } catch (IOException e) {
            log.warn("article count failed.", e);
        }
        return 0;
    }

    private List<Article> restQuery(SearchRequest searchRequest) throws IOException{
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = searchResponse.getHits().getHits();
        return EsClientUtils.convertAndSortByScore(hits).stream()
                .map(obj -> JSONObject.parseObject(JSONObject.toJSONString(obj), Article.class))
                .collect(Collectors.toList());
    }

    private BoolQueryBuilder buildFuzzQueryBuilder(EsArticleCondition esArticleCondition) {
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        if (Strings.isNotEmpty(esArticleCondition.getTitle())) {
            // 模糊匹配
            MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(EsArticleCondition.DOCUMENT_FILED_TITLE, esArticleCondition.getTitle());
            boolQueryBuilder.filter(queryBuilder);
        }

        if (Strings.isNotEmpty(esArticleCondition.getContent())) {
            // 模糊匹配
            MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(EsArticleCondition.DOCUMENT_FILED_CONTENT, esArticleCondition.getContent());
            boolQueryBuilder.filter(queryBuilder);
        }

        if (Strings.isNotEmpty(esArticleCondition.getTheme())) {
            // 精准匹配
            boolQueryBuilder.filter(QueryBuilders.termQuery(EsArticleCondition.DOCUMENT_FILED_THEME + ".keyword", esArticleCondition.getTheme()));
        }

        if (Strings.isNotEmpty(esArticleCondition.getKeywords())) {
            // 模糊匹配
            MatchPhraseQueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery(EsArticleCondition.DOCUMENT_FILED_KEYWORDS, esArticleCondition.getKeywords());
            boolQueryBuilder.filter(queryBuilder);
        }
        return boolQueryBuilder;
    }
}
