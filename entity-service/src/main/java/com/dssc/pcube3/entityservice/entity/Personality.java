package com.dssc.pcube3.entityservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personality {
    String entityId;
    float openness;  // 开放性分值
    float conscientious;  // 尽责性分值
    float extraversion;  // 外向性分值
    float agreeableness;  // 宜人性分值
    float neuroticism;  // 神经质分值
}
