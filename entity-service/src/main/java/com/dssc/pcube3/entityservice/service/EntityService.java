package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.ArticleToEntityRelationDao;
import com.dssc.pcube3.entityservice.dao.EsEntityClient;
import com.dssc.pcube3.entityservice.entity.Celebrity;
import com.dssc.pcube3.entityservice.entity.Entity;
import com.dssc.pcube3.entityservice.entity.EsEntityCondition;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntityService {

    @Autowired
    private EsEntityClient esEntityClient;
    @Autowired
    private ArticleToEntityRelationDao articleToEntityRelationDao;

    public Celebrity queryCelebrityById(String id) {
        return esEntityClient.queryCelebrityById(id);
    }

    public Entity queryEntityById(String id) {
        return esEntityClient.queryEntityById(id);
    }

    public List<Celebrity> queryCelebrity(EsEntityCondition esCelebrityCondition) {
        return esEntityClient.queryCelebrity(esCelebrityCondition);
    }

    public List<Entity> queryEntity(EsEntityCondition esCelebrityCondition) {
        return esEntityClient.queryEntity(esCelebrityCondition);
    }

    public List<String> getAidsByEid(String eid) {
        return articleToEntityRelationDao.getArticleIdsByEntityId(eid);
    }

    public long countData() {
        return esEntityClient.countData();
    }
}
