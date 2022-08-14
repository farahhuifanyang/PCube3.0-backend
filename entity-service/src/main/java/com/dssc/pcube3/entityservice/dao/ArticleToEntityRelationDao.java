package com.dssc.pcube3.entityservice.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleToEntityRelationDao extends HBaseBaseDao{

    private final String TABLE_NAME = "PCube_ArticleEntity";
    private final String FAMILY_NAME = "eid";

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public List<String> getArticleIdsByEntityId(String eid) {
        Scan scan = new Scan();
        SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(FAMILY_NAME.getBytes(), FAMILY_NAME.getBytes(), CompareFilter.CompareOp.EQUAL, eid.getBytes());
        scan.setFilter(singleColumnValueFilter);

        return hbaseTemplate.find(TABLE_NAME, scan, getRowKeyMapper)
                .stream().filter(StringUtils::isNotBlank)
                // 从Hbase中取出时行键为 aid_eid ,需要截取
                .map(str -> str.split("_")[0])
                .collect(Collectors.toList());
    }
}
