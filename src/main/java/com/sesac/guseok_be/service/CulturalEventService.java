package com.sesac.guseok_be.service;

import com.sesac.guseok_be.entity.CulturalEventEntity;
import com.sesac.guseok_be.repository.CulturalEventRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturalEventService {

    @Autowired
    CulturalEventRepository culturalEventRepository;

    public List<CulturalEventEntity> getEventList() {
        //        return culturalEventRepository.findEventsAfterToday();
        return culturalEventRepository.findEventsAfterToday().stream().limit(10).toList();
//        return culturalEventRepository.findAll().stream().limit(10).toList();

    }
}
