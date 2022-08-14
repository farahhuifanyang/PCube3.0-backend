package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.LocationDao;
import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationService extends BaseNetworkService {
    @Autowired
    private LocationDao locationDao;
    @Override
    public List<NodeDTO> getNodesList(String eid) {
        return locationDao.getNodesList(eid);
    }
    @Override
    public List<LinkDTO> getLinksList(String eid) {
        return locationDao.getLinksList(eid);
    }
    @Override
    public NodeDTO getTargetNode(String eid) {
        return locationDao.getTargetNode(eid);
    }

    @Override
    protected Integer getNodesCount() {
        return locationDao.getNodesCount();
    }

    @Override
    protected Integer getLinksCount() {
        return locationDao.getLinksCount();
    }

}
