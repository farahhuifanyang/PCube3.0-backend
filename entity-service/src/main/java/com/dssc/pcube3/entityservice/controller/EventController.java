package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.dataVO.EventVO;
import com.dssc.pcube3.entityservice.service.EventService;
import com.dssc.pcube3.entityservice.wrapper.EventVOWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private EventVOWrapper eventVOWrapper;

    @GetMapping("/{id}")
    public EventVO queryById(@PathVariable String id) {
        return eventVOWrapper.wrap(eventService.queryById(id));
    }

    @GetMapping()
    public List<EventVO> queryByKeyword(@RequestParam String keyword) {
        return eventService.queryByKeyword(keyword).stream().map(eventVOWrapper::wrap).collect(Collectors.toList());
    }

    @GetMapping("/latest")
    public List<EventVO> queryLatest(@RequestParam(required = false) Integer limit,
                                   @RequestParam(defaultValue = "台湾") String keyword) {
        if (limit == null || limit == 0) {
            limit = 10;
        }
        return eventService.queryLatest(keyword, limit).stream().map(eventVOWrapper::wrap).collect(Collectors.toList());
    }

    //
    @GetMapping("/byeid/{entityId}")
    public List<EventVO> queryByEid(@PathVariable String entityId) {
        return eventService.queryByEntityId(entityId).stream().map(eventVOWrapper::wrap).collect(Collectors.toList());
    }
}
