package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.entity.Celebrity;
import com.dssc.pcube3.entityservice.entity.Entity;
import com.dssc.pcube3.entityservice.entity.EsEntityCondition;
import com.dssc.pcube3.entityservice.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/entity")
public class EntityController {

    @Autowired
    private EntityService entityService;

    @GetMapping("/celebrity/condition")
    public List<Celebrity> getCelebrityByCondition(@RequestParam(required = true) String name) {
        EsEntityCondition esCelebrityCondition = new EsEntityCondition(name);
        esCelebrityCondition.setName(name);
        return entityService.queryCelebrity(esCelebrityCondition);
    }

    @GetMapping("/condition")
    public List<Entity> getEntityByCondition(@RequestParam(required = true) String name) {
        EsEntityCondition esCelebrityCondition = new EsEntityCondition(name);
        esCelebrityCondition.setName(name);
        return entityService.queryEntity(esCelebrityCondition);
    }

    @GetMapping("/{id}")
    public Entity getEntityById(@PathVariable String id) {
        return entityService.queryEntityById(id);
    }

    @GetMapping("/celebrity/{id}")
    public Celebrity getCelebrityById(@PathVariable String id) {
        return entityService.queryCelebrityById(id);
    }
}
