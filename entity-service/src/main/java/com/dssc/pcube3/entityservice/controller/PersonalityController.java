package com.dssc.pcube3.entityservice.controller;

import com.dssc.pcube3.entityservice.entity.Personality;
import com.dssc.pcube3.entityservice.service.PersonalityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/personality")
public class PersonalityController {

    @Autowired
    private PersonalityService personalityService;

    @GetMapping("/{id}")
    public ResponseEntity<Personality> getPersonality(@PathVariable String id) {
        return new ResponseEntity<>(personalityService.find(id), HttpStatus.OK);
    }

    @PostMapping("/save")
    public void savePersonality(@RequestParam String id,
                                @RequestParam String openness,
                                @RequestParam String conscientious,
                                @RequestParam String extraversion,
                                @RequestParam String agreeableness,
                                @RequestParam String neuroticism) {
        personalityService.put(id, openness, conscientious, extraversion, agreeableness, neuroticism);
    }

    @DeleteMapping("/{id}")
    public void deletePersonality(@PathVariable String id) {
        personalityService.delete(id);
    }
}
