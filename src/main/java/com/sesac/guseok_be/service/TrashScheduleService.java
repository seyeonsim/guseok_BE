package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.TrashSchedule;
import com.sesac.guseok_be.repository.TrashScheduleRepository;

import java.util.List;

public interface TrashScheduleService {
    public List<TrashSchedule> getAllTrashSchedule();

    public List<TrashSchedule> getSchedulesByDistrict(String district);

    public List<String> getDistricts();
}
