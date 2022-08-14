package com.dssc.pcube3.entityservice.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class Celebrity {

    private String eid;

    private String name;

    private String summary;

    private String name_cht;

    private List<String> alias;

    private String gender;

    private String birthDay;

    private String birthPlace;

    private String father;

    private String mother;

    private String spouse;

    private String sibling;

    private List<String> child;

    private String ancestralHome;

    private List<String> occupation;

    private String party;

    private JSONObject education;

    private JSONObject position;

    private JSONObject webSite;

    private String outLink;

    private String image;
}
