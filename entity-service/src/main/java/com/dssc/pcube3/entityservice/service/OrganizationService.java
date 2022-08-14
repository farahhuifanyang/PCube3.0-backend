package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.dao.OrganizationDao;
import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService extends BaseNetworkService {
    @Autowired
    private OrganizationDao organizationDao;
    @Override
    public List<NodeDTO> getNodesList(String eid) {
        return organizationDao.getNodesList(eid);
    }
    @Override
    public List<LinkDTO> getLinksList(String eid) {
        return organizationDao.getLinksList(eid);
    }
    @Override
    public NodeDTO getTargetNode(String eid) {
        return organizationDao.getTargetNode(eid);
    }

    @Override
    protected Integer getNodesCount() {
        return organizationDao.getNodesCount();
    }

    @Override
    protected Integer getLinksCount() {
        return organizationDao.getLinksCount();
    }

}
