package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.service.SixSearchService;
import com.dssc.pcube3.entityservice.wrapper.SixSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "sixsearch")
public class SixSearchController {

    @Autowired
    private SixSearchService sixSearchService;

    @GetMapping("/result")
    public SixSearchDTO getAllNetworks(@RequestParam String startEid, @RequestParam String endEid) {
        return sixSearchService.getSixSearch(startEid, endEid);
    }

}
