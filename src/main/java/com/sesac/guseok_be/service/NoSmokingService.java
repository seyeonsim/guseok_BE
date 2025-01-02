package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.NoSmoking;
import com.sesac.guseok_be.repository.NoSmokingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoSmokingService {

    private final NoSmokingRepository noSmokingRepository;
    public NoSmokingService(NoSmokingRepository noSmokingRepository) {this.noSmokingRepository = noSmokingRepository;}

    public List<String> getDistinctDistricts() {
        return noSmokingRepository.findDistinctDistricts();
    }

    public List<NoSmoking> getDataByDistrict(String district) {
        return noSmokingRepository.findDataByDistrict(district);
    }

    public List<NoSmoking> getAllData() {
        return noSmokingRepository.findAllData();
    }
}
