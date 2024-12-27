package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.entity.CulturalEventEntity;
import com.sesac.guseok_be.service.CulturalEventService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CulturalEventController {

    @Autowired
    CulturalEventService culturalEventService;

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/event")
    public List<CulturalEventEntity> getEventList(@RequestParam String district) {
        return culturalEventService.getEventList(district);
    }

}

