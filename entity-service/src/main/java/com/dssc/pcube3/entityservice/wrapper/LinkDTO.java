package com.dssc.pcube3.entityservice.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkDTO {
    String source;
    String target;
    String value;
    String des;
}
