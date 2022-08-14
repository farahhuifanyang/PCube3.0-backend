package com.dssc.pcube3.entityservice.service;

import com.dssc.pcube3.entityservice.entity.Statistic;
import com.dssc.pcube3.entityservice.enums.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private EntityService entityService;
    @Autowired
    private EventService eventService;
    @Autowired
    private PersonalityService personalityService;
    @Autowired
    private PersonService personService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private OrganizationService organizationService;

    public List<Statistic> getDataStatistic() {
        List<Statistic> statistics = new ArrayList<>();
        statistics.add(new Statistic(Resource.ARTICLE.toString(), articleService.countData()));
        statistics.add(new Statistic(Resource.ENTITY.toString(), entityService.countData()));
        statistics.add(new Statistic(Resource.EVENT.toString(), eventService.countData()));
        statistics.add(new Statistic(Resource.PERSONALITY.toString(), personalityService.countData()));
        statistics.addAll(getNetworkStatistic());
        return statistics;
    }

    public List<Statistic> getNetworkStatistic() {
        List<Statistic> statistics = new ArrayList<>();
        statistics.add(new Statistic(Resource.LOCATION_NODE.toString(), locationService.getNodesCount()));
        statistics.add(new Statistic(Resource.LOCATION_LINK.toString(), locationService.getLinksCount()));
        statistics.add(new Statistic(Resource.PERSON_NODE.toString(), personService.getNodesCount()));
        statistics.add(new Statistic(Resource.PERSON_LINK.toString(), personService.getLinksCount()));
        statistics.add(new Statistic(Resource.ORGANIZATION_NODE.toString(), organizationService.getNodesCount()));
        statistics.add(new Statistic(Resource.ORGANIZATION_LINK.toString(), organizationService.getLinksCount()));
        statistics.add(new Statistic(Resource.REGION_NODE.toString(), regionService.getNodesCount()));
        statistics.add(new Statistic(Resource.REGION_LINK.toString(), regionService.getLinksCount()));
        return statistics;
    }
}
