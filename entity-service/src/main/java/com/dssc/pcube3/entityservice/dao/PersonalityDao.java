package com.dssc.pcube3.entityservice.dao;

import com.dssc.pcube3.entityservice.configuration.HBaseConfiguration;
import com.dssc.pcube3.entityservice.entity.Personality;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.client.coprocessor.AggregationClient;
import org.apache.hadoop.hbase.client.coprocessor.LongColumnInterpreter;
import org.apache.hadoop.hbase.filter.FirstKeyOnlyFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class PersonalityDao extends HBaseBaseDao{
    @Autowired
    private HbaseTemplate hbaseTemplate;
    private final String TABLE_NAME = "PCube_Personality";
    private final String FAMILY_NAME = "Personality_details";

    private static final byte[] COLUMN_FAMILY = "Personality_details".getBytes();

    private static final String OPENNESS = "openness";
    private static final String CONSCIENTIOUS = "conscientious";
    private static final String EXTRAVERSION = "extraversion";
    private static final String AGREEABLENESS = "agreeableness";
    private static final String NEUROTICISM = "neuroticism";

    /**
     * 查
     *
     * @param entityId
     * @return
     */
    public Personality find(String entityId) {
        Personality personality = hbaseTemplate.get(TABLE_NAME, entityId, FAMILY_NAME, rowMapper);
        personality.setEntityId(entityId);
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
        hbaseTemplate.put(TABLE_NAME, entityId, FAMILY_NAME, "openness", Bytes.toBytes(openness));
        hbaseTemplate.put(TABLE_NAME, entityId, FAMILY_NAME, "conscientious", Bytes.toBytes(conscientious));
        hbaseTemplate.put(TABLE_NAME, entityId, FAMILY_NAME, "extraversion", Bytes.toBytes(extraversion));
        hbaseTemplate.put(TABLE_NAME, entityId, FAMILY_NAME, "agreeableness", Bytes.toBytes(agreeableness));
        hbaseTemplate.put(TABLE_NAME, entityId, FAMILY_NAME, "neuroticism", Bytes.toBytes(neuroticism));
    }

    /**
     * 删
     *
     * @param entityId
     */
    public void delete(String entityId) {
        hbaseTemplate.delete(TABLE_NAME, entityId, FAMILY_NAME);
    }

    // 如果数据量较大 10W甚至100W以上量级 可以用这个
    public long countBigAmount() throws Throwable {
        Admin admin = HBaseConfiguration.connection.getAdmin();
        TableName name = TableName.valueOf(TABLE_NAME);
        admin.disableTable(name);
        HTableDescriptor descriptor = admin.getTableDescriptor(name);
        String coprocessorClass = "org.apache.hadoop.hbase.coprocessor.AggregateImplementation";
        if (! descriptor.hasCoprocessor(coprocessorClass)) {
            descriptor.addCoprocessor(coprocessorClass);
        }
        admin.modifyTable(name, descriptor);
        admin.enableTable(name);

        Scan scan = new Scan();
        AggregationClient aggregationClient = new AggregationClient(admin.getConfiguration());
        return aggregationClient.rowCount(name, new LongColumnInterpreter(), scan);
    }

    // 小数据量直接遍历
    public long countSmallAmount() {
        TableName tableName = TableName.valueOf(TABLE_NAME);
        long rowCount = 0;
        try {
            Table table = HBaseConfiguration.connection.getTable(tableName);
            Scan scan = new Scan();
            scan.setFilter(new FirstKeyOnlyFilter());
            ResultScanner rs = table.getScanner(scan);
            for (Result r : rs) {
                rowCount += r.size();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    private static final RowMapper<Personality> rowMapper = (result, rowNum) -> {
        Map<String, Object> map = getQualifier2ValueMap(result);

        Personality personality = new Personality();
        personality.setOpenness(Float.parseFloat((String) map.getOrDefault(OPENNESS, "0")));
        personality.setConscientious(Float.parseFloat((String) map.getOrDefault(CONSCIENTIOUS, "0")));
        personality.setExtraversion(Float.parseFloat((String) map.getOrDefault(EXTRAVERSION, "0")));
        personality.setAgreeableness(Float.parseFloat((String) map.getOrDefault(AGREEABLENESS, "0")));
        personality.setNeuroticism(Float.parseFloat((String) map.getOrDefault(NEUROTICISM, "0")));
        return personality;
    };
}
