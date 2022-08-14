package com.dssc.pcube3.entityservice.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class NetworkDTO {
    List<NodeDTO> nodes;
    List<LinkDTO> links;
}
