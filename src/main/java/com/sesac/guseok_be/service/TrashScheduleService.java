package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.TrashSchedule;
import com.sesac.guseok_be.repository.TrashScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrashScheduleService {
    private final TrashScheduleRepository repository;

    @Autowired
    public TrashScheduleService(TrashScheduleRepository repository) {
        this.repository = repository;
    }

    public List<TrashSchedule> getAllTrashSchedule() {
        return repository.findAll();
    }

    public List<TrashSchedule> getSchedulesByDistrict(String district) {
        return repository.findDataByDistrict(district);
    }

    public List<String> getDistricts() {
        return repository.findDistinctDistricts();
    }
}
