package com.dssc.pcube3.entityservice.logic;

import com.dssc.pcube3.entityservice.service.LocationService;
import com.dssc.pcube3.entityservice.service.OrganizationService;
import com.dssc.pcube3.entityservice.service.PersonService;
import com.dssc.pcube3.entityservice.service.RegionService;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetworkLogic {

    @Autowired
    private PersonService personService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private OrganizationService organizationService;

    public NetworkDTO getRelNetwork(String eid) {
        NetworkDTO network = personService.getRelNetwork(eid);
        if (network != null) {
            return network;
        }
        network = locationService.getRelNetwork(eid);
        if (network != null) {
            return network;
        }
        network = regionService.getRelNetwork(eid);
        if (network != null) {
            return network;
        }
        network = organizationService.getRelNetwork(eid);
        return network;
    }
}
