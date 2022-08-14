package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.entity.Statistic;
import com.dssc.pcube3.entityservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/statistic")
    public List<Statistic> getStatistic() {
        return adminService.getDataStatistic();
    }
}
