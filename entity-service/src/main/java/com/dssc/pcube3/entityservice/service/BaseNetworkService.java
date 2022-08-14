package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public abstract class BaseNetworkService {
    protected abstract List<NodeDTO> getNodesList(String eid);

    protected abstract List<LinkDTO> getLinksList(String eid);

    protected abstract NodeDTO getTargetNode(String eid);

    protected abstract Integer getNodesCount();

    protected abstract Integer getLinksCount();

    public NetworkDTO getRelNetwork(String eid) {
        NodeDTO nodeDTO = getTargetNode(eid);
        if (nodeDTO == null) {
            return null;
        }
        NetworkDTO networkDTO = new NetworkDTO();
        List<NodeDTO> nodeDTOList = getNodesList(eid);
        nodeDTOList.add(0, nodeDTO);
        networkDTO.setNodes(nodeDTOList);
        networkDTO.setLinks(getLinksList(eid));
        return networkDTO;
    }

}
