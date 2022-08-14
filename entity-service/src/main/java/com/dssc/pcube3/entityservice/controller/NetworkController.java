package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.logic.NetworkLogic;
import com.dssc.pcube3.entityservice.wrapper.NetworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/network")
public class NetworkController {
    @Autowired
    private NetworkLogic networkLogic;

    @GetMapping(path = "/{eid}")
    public NetworkDTO getRelNetwork(@PathVariable String eid) {
        return networkLogic.getRelNetwork(eid);
    }
}
