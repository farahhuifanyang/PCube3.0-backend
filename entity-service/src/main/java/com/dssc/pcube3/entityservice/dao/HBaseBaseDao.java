package com.dssc.pcube3.entityservice.dao;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;
import org.springframework.data.hadoop.hbase.RowMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HBaseBaseDao {

    public static final RowMapper<String> getRowKeyMapper = (result, rowNum) -> {
        if (result.isEmpty()) {
            return StringUtils.EMPTY;
        }

        List<Cell> ceList = result.listCells();
        if (CollectionUtils.isEmpty(ceList)) {
            return StringUtils.EMPTY;
        }

        Cell cell = ceList.get(0);
        String rowKey = Bytes.toString(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
        return rowKey;
    };

    // 暂时没有列族的概念
    public static Map<String, Object> getQualifier2ValueMap(Result result) {
        if (result.isEmpty()) {
            return new HashMap<>();
        }

        List<Cell> ceList = result.listCells();
        Map<String, Object> map = new HashMap<>();
        if (ceList != null && ceList.size() > 0) {
            for (Cell cell : ceList) {
                map.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()),
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
        }
        return map;
    }
}
