package com.sesac.guseok_be.service;

import com.sesac.guseok_be.entity.CulturalEventEntity;
import com.sesac.guseok_be.repository.CulturalEventRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CulturalEventService {

    @Autowired
    CulturalEventRepository culturalEventRepository;

    public List<CulturalEventEntity> getEventList(String district, Integer limit) {
//        return culturalEventRepository.findByEndDateAfterAndDistrict(LocalDate.now(), district).stream().limit(10).toList();
        List<CulturalEventEntity> events = culturalEventRepository.findByEndDateAfterAndDistrict(LocalDate.now(), district);

        if (limit != null) {
            // limit 파라미터가 존재하면 해당 개수만큼 제한
            return events.stream().limit(limit).toList();
        }

        return events;
    }
}
