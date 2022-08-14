package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.PersonalityDao;
import com.dssc.pcube3.entityservice.entity.Personality;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PersonalityService {
    @Autowired
    PersonalityDao personalityDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 查
     *
     * @param entityId
     * @return
     */
    public Personality find(String entityId) {
        Personality personality;
        try {
            personality = personalityDao.find(entityId);
        } catch (Exception e) {
            logger.error("User:{} not found, e:{}", entityId, e);
            return null;
        }
        return personality;
    }

    /**
     * 增
     *
     * @param entityId
     * @param openness
     * @param conscientious
     * @param extraversion
     * @param agreeableness
     * @param neuroticism
     */
    public void put(String entityId, String openness, String conscientious, String extraversion, String agreeableness,
                    String neuroticism) {
        personalityDao.put(entityId, openness, conscientious, extraversion, agreeableness, neuroticism);
    }

    /**
     * 删
     *
     * @param entityId
     */
    public void delete(String entityId) {
        personalityDao.delete(entityId);
    }

    public long countData() {
        // 现有数据量较小
        return personalityDao.countSmallAmount();
    }
}
