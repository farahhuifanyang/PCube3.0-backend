package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.SixSearchDao;
import com.dssc.pcube3.entityservice.wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SixSearchService {
    @Autowired
    private SixSearchDao sixSearchDao;


    List<PathDTO> pathDTOS = new ArrayList<>();


    public NetworkDTO getFirstRelations(String startEid, String endEid) {
        List<LinkDTO> linkDTOList = new ArrayList<>();
        List<SearchDTO> sixSearch2DTOS = sixSearchDao.getFirstRelations(startEid, endEid);
        sixSearch2DTOS.forEach(item -> {
            linkDTOList.add(item.getFirstLink());
        });
        List<NodeDTO> nodeDTOList = new ArrayList<>();
        sixSearch2DTOS.forEach(item -> {
            nodeDTOList.add(item.getStartNode());
            nodeDTOList.add(item.getFirstNode());
        });
        sixSearch2DTOS.forEach(item -> {
            pathDTOS.add(new PathDTO(1, "[" + item.getStartNodeName() + "]," + item.getFirstLinkName() + ",["
                    + item.getFirstNodeName() + "],"));
        });
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setNodes(nodeDTOList.stream().distinct().collect(Collectors.toList()));
        networkDTO.setLinks(linkDTOList.stream().distinct().collect(Collectors.toList()));

        if (networkDTO.getNodes().isEmpty()) {
            networkDTO.setLinks(null);
            networkDTO.setNodes(null);
        }
        return networkDTO;
    }

    public NetworkDTO getSecondRelations(String startEid, String endEid) {
        List<LinkDTO> linkDTOList = new ArrayList<>();
        List<SearchDTO> sixSearch2DTOS = sixSearchDao.getSecondRelations(startEid, endEid);
        sixSearch2DTOS.forEach(item -> {
            linkDTOList.add(item.getFirstLink());
            linkDTOList.add(item.getSecondLink());
        });
        List<NodeDTO> nodeDTOList = new ArrayList<>();
        sixSearch2DTOS.forEach(item -> {
            nodeDTOList.add(item.getStartNode());
            nodeDTOList.add(item.getFirstNode());
            nodeDTOList.add(item.getSecondNode());
        });
        sixSearch2DTOS.forEach(item -> {
            pathDTOS.add(new PathDTO(2, "[" + item.getStartNodeName() + "]," + item.getFirstLinkName() + ",["
                    + item.getFirstNodeName() + "]," + item.getSecondLinkName() + ",[" + item.getSecondNodeName() + "],"));
        });
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setNodes(nodeDTOList.stream().distinct().collect(Collectors.toList()));
        networkDTO.setLinks(linkDTOList.stream().distinct().collect(Collectors.toList()));

        if (networkDTO.getNodes().isEmpty()) {
            networkDTO.setLinks(null);
            networkDTO.setNodes(null);
        }
        return networkDTO;
    }

    public NetworkDTO getThirdRelations(String startEid, String endEid) {
        List<LinkDTO> linkDTOList = new ArrayList<>();
        List<SearchDTO> sixSearch2DTOS = sixSearchDao.getThirdRelations(startEid, endEid);
        sixSearch2DTOS.forEach(item -> {
            linkDTOList.add(item.getFirstLink());
            linkDTOList.add(item.getSecondLink());
            linkDTOList.add(item.getThirdLink());
        });
        List<NodeDTO> nodeDTOList = new ArrayList<>();
        sixSearch2DTOS.forEach(item -> {
            nodeDTOList.add(item.getStartNode());
            nodeDTOList.add(item.getFirstNode());
            nodeDTOList.add(item.getSecondNode());
            nodeDTOList.add(item.getThirdNode());
        });
        sixSearch2DTOS.forEach(item -> {
            pathDTOS.add(new PathDTO(3, "[" + item.getStartNodeName() + "]," + item.getFirstLinkName() + ",["
                    + item.getFirstNodeName() + "]," + item.getSecondLinkName() + ",[" + item.getSecondNodeName() + "],"
                    + item.getThirdLinkName() + ",[" + item.getThirdNodeName() + "],"));
        });
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setNodes(nodeDTOList.stream().distinct().collect(Collectors.toList()));
        networkDTO.setLinks(linkDTOList.stream().distinct().collect(Collectors.toList()));

        if (networkDTO.getNodes().isEmpty()) {
            networkDTO.setLinks(null);
            networkDTO.setNodes(null);
        }
        return networkDTO;
    }

    public NetworkDTO getFourthRelations(String startEid, String endEid) {
        List<LinkDTO> linkDTOList = new ArrayList<>();
        List<SearchDTO> sixSearch2DTOS = sixSearchDao.getFourthRelations(startEid, endEid);
        sixSearch2DTOS.forEach(item -> {
            linkDTOList.add(item.getFirstLink());
            linkDTOList.add(item.getSecondLink());
            linkDTOList.add(item.getThirdLink());
            linkDTOList.add(item.getFourthLink());
        });
        List<NodeDTO> nodeDTOList = new ArrayList<>();
        sixSearch2DTOS.forEach(item -> {
            nodeDTOList.add(item.getStartNode());
            nodeDTOList.add(item.getFirstNode());
            nodeDTOList.add(item.getSecondNode());
            nodeDTOList.add(item.getThirdNode());
            nodeDTOList.add(item.getFourthNode());
        });
        sixSearch2DTOS.forEach(item -> {
            pathDTOS.add(new PathDTO(4, "[" + item.getStartNodeName() + "]," + item.getFirstLinkName() + ",["
                    + item.getFirstNodeName() + "]," + item.getSecondLinkName() + ",[" + item.getSecondNodeName() + "],"
                    + item.getThirdLinkName() + ",[" + item.getThirdNodeName() + "],"
                    + item.getFourthLinkName() + ",[" + item.getFourthNodeName() + "],"));
        });
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setNodes(nodeDTOList.stream().distinct().collect(Collectors.toList()));
        networkDTO.setLinks(linkDTOList.stream().distinct().collect(Collectors.toList()));

        if (networkDTO.getNodes().isEmpty()) {
            networkDTO.setLinks(null);
            networkDTO.setNodes(null);
        }
        return networkDTO;
    }

    public NetworkDTO getFifthRelations(String startEid, String endEid) {
        List<LinkDTO> linkDTOList = new ArrayList<>();
        List<SearchDTO> sixSearch2DTOS = sixSearchDao.getFifthRelations(startEid, endEid);
        sixSearch2DTOS.forEach(item -> {
            linkDTOList.add(item.getFirstLink());
            linkDTOList.add(item.getSecondLink());
            linkDTOList.add(item.getThirdLink());
            linkDTOList.add(item.getFourthLink());
            linkDTOList.add(item.getFifthLink());
        });
        List<NodeDTO> nodeDTOList = new ArrayList<>();
        sixSearch2DTOS.forEach(item -> {
            nodeDTOList.add(item.getStartNode());
            nodeDTOList.add(item.getFirstNode());
            nodeDTOList.add(item.getSecondNode());
            nodeDTOList.add(item.getThirdNode());
            nodeDTOList.add(item.getFourthNode());
            nodeDTOList.add(item.getFifthNode());
        });
        sixSearch2DTOS.forEach(item -> {
            pathDTOS.add(new PathDTO(5, "[" + item.getStartNodeName() + "]," + item.getFirstLinkName() + ",["
                    + item.getFirstNodeName() + "]," + item.getSecondLinkName() + ",[" + item.getSecondNodeName() + "],"
                    + item.getThirdLinkName() + ",[" + item.getThirdNodeName() + "],"
                    + item.getFourthLinkName() + ",[" + item.getFourthNodeName() + "],"
                    + item.getFifthLinkName() + ",[" + item.getFifthNodeName() + "],"));
        });
        NetworkDTO networkDTO = new NetworkDTO();
        networkDTO.setNodes(nodeDTOList.stream().distinct().collect(Collectors.toList()));
        networkDTO.setLinks(linkDTOList.stream().distinct().collect(Collectors.toList()));

        if (networkDTO.getNodes().isEmpty()) {
            networkDTO.setLinks(null);
            networkDTO.setNodes(null);
        }
        return networkDTO;
    }

    public SixSearchDTO getSixSearch(String startEid, String endEid) {
        SixSearchDTO sixSearchDTO = new SixSearchDTO();
        pathDTOS.clear();
        List<NetworkDTO> networkDTOList = new ArrayList<>();
        networkDTOList.add(getFirstRelations(startEid, endEid));
        networkDTOList.add(getSecondRelations(startEid, endEid));
        networkDTOList.add(getThirdRelations(startEid, endEid));
        networkDTOList.add(getFourthRelations(startEid, endEid));
        networkDTOList.add(getFifthRelations(startEid, endEid));
        sixSearchDTO.setNetworkDTOList(networkDTOList);
        sixSearchDTO.setPathDTOList(pathDTOS);
        return sixSearchDTO;
    }
}
