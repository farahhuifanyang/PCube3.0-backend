package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.RegionDao;
import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService extends BaseNetworkService {
    @Autowired
    private RegionDao regionDao;
    @Override
    public List<NodeDTO> getNodesList(String eid) {
        return regionDao.getNodesList(eid);
    }
    @Override
    public List<LinkDTO> getLinksList(String eid) {
        return regionDao.getLinksList(eid);
    }
    @Override
    public NodeDTO getTargetNode(String eid) {
        return regionDao.getTargetNode(eid);
    }

    @Override
    protected Integer getNodesCount() {
        return regionDao.getNodesCount();
    }

    @Override
    protected Integer getLinksCount() {
        return regionDao.getLinksCount();
    }

}
