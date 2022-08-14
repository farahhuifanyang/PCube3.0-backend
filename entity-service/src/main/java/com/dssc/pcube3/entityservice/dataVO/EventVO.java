package com.dssc.pcube3.entityservice.dataVO;

import lombok.Data;

import java.util.List;

@Data
public class EventVO {

    private String eid;

    private String abstractText;

    private String name;

    private String crawlingTime;

    private List<String> relatedImgList;

    private String timeStamp;
}
