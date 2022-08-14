package com.dssc.pcube3.entityservice.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Event {

    private String eid;

    // abstract是关键字，需要做转换
    @JSONField(name = "abstract")
    private String abstractText;

    private String name;

    private String timestamp;

    private List<String> relatedImgList;
}
