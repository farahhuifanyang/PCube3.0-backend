package com.dssc.pcube3.entityservice.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Log4j2
public class EventToArticleRelationDao extends HBaseBaseDao{

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private final String TABLE_NAME = "PCube_Event";
    private final String FAMILY_NAME = "Event_details";
    private static final String AID = "aid";

    public String[] findAidsByEventId(String eventId) {
        String aid = hbaseTemplate.get(TABLE_NAME, eventId, FAMILY_NAME, rowMapper).trim();

        // 这里如果aid为空串，split之后的数组为[null], length为1，影响后续判断
        if (StringUtils.isBlank(aid)) {
            return null;
        }

        return aid.split(",");
    }

    private static final RowMapper<String> rowMapper = (result, rowNum) -> {
        Map<String, Object> map = getQualifier2ValueMap(result);
        return (String) map.getOrDefault(AID, "");
    };
}
