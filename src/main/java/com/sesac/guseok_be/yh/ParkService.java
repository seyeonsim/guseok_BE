package com.sesac.guseok_be.yh;

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
}
