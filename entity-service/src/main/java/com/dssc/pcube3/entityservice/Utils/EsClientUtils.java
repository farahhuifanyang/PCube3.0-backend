package com.dssc.pcube3.entityservice.Utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.ArrayUtils;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EsClientUtils {

    public static List<Object> convertAndSortByScore(SearchHit[] searchHits) {
        // 根据score倒序排序，之后可以让前端选，相关度排序/时间最新排序
        Arrays.sort(searchHits, (h1, h2) -> (int) (h2.getScore() - h1.getScore()));
        return convertSource2List(searchHits);
    }

    public static List<Object> convertSource2List(SearchHit[] searchHits) {
        if (ArrayUtils.isEmpty(searchHits)) {
            return new ArrayList<>();
        }

        List<Object> resultList = new ArrayList<>(searchHits.length);
        for (SearchHit hit : searchHits) {
            String jsonString = hit.getSourceAsString();
            Object obj = JSONObject.parseObject(jsonString, Object.class);
            resultList.add(obj);
        }
        return resultList;
    }
}
