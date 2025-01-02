package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.NoSmoking;
import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.repository.NoSmokingRepository;
import com.sesac.guseok_be.repository.SmokingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SmokingService {

    private final SmokingRepository smokingRepository;

    @Autowired
    public SmokingService(SmokingRepository smokingRepository) {
        this.smokingRepository = smokingRepository;
    }

    public List<String> getDistinctDistricts() {
        return smokingRepository.findDistinctDistricts();
    }

    public List<Smoking> getDataByDistrict(String district) {
        return smokingRepository.findDataByDistrict(district);
    }

    public List<Smoking> getAllData() {
        return smokingRepository.findAllData();
    }
}
