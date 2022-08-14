package com.dssc.pcube3.entityservice.dao;

import com.dssc.pcube3.entityservice.configuration.HBaseConfiguration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArticleToEventRelationDao extends HBaseBaseDao{

    private final String TABLE_NAME = "PCube_ArticleEvent";

    public List<String> getEventIdsByAids(List<String> aids) throws IOException {
        Table table = HBaseConfiguration.connection.getTable(TableName.valueOf(TABLE_NAME));
        List<Get> gets = new ArrayList<>();
        for (String aid : aids) {
            gets.add(new Get(aid.getBytes()));
        }
        Result[] results = table.get(gets);

        List<String> eventIds = new ArrayList<>();
        for (Result result : results){
            // 暂时表里只有一列，直接取value就行
            for (Cell cell : result.rawCells()) {
                String value = Bytes.toString(CellUtil.cloneValue(cell));
                eventIds.add(value);
            }
        }
        return eventIds;
    }
}
