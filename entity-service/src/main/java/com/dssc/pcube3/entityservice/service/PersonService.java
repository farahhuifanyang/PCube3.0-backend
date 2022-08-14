package com.dssc.pcube3.entityservice.service;


import com.dssc.pcube3.entityservice.dao.PersonDao;
import com.dssc.pcube3.entityservice.wrapper.LinkDTO;
import com.dssc.pcube3.entityservice.wrapper.NodeDTO;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class PersonService extends BaseNetworkService {
    @Autowired
    private PersonDao personDao;
    @Override
    public List<NodeDTO> getNodesList(String eid) {
        return personDao.getNodesList(eid);
    }
    @Override
    public List<LinkDTO> getLinksList(String eid) {
        return personDao.getLinksList(eid);
    }
    @Override
    public NodeDTO getTargetNode(String eid) {
        return personDao.getTargetNode(eid);
    }

    @Override
    protected Integer getNodesCount() {
        return personDao.getNodesCount();
    }

    @Override
    protected Integer getLinksCount() {
        return personDao.getLinksCount();
    }
}
