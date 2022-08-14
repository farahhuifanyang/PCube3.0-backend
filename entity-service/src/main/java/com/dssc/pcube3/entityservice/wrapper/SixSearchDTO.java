package com.dssc.pcube3.entityservice.wrapper;

import lombok.Data;

import java.util.List;

@Data
public class SixSearchDTO {
    List<NetworkDTO> networkDTOList;
    List<PathDTO> pathDTOList;
}
