package com.sesac.guseok_be.service;

import com.sesac.guseok_be.entity.ParkEntity;
import com.sesac.guseok_be.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkService {

    @Autowired
    private ParkRepository parkRepository;

    public List<ParkEntity> getAllParks() {
        return parkRepository.findAll();
    }

    public List<ParkEntity> getParksByDistrict(String district) {
        return parkRepository.findByDistrict(district);
    }

    public Optional<ParkEntity> findParkById(Long id) {
        return parkRepository.findById(id);
    }

    public List<ParkEntity> findMainPark(String district, Integer limit) {
        List<ParkEntity> parks = parkRepository.findByDistrict(district);

        if (limit != null) {
            // limit 파라미터가 존재하면 해당 개수만큼 제한
            return parks.stream().limit(limit).toList();
        }

        return parks;
    }
}
