package com.sesac.guseok_be.service;

import com.sesac.guseok_be.entity.DistrictEntity;
import com.sesac.guseok_be.repository.DistrictRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

    @Autowired
    DistrictRepository districtRepository;

    public List<DistrictEntity> getDistricts() {
        return districtRepository.findAll();
    }
}
