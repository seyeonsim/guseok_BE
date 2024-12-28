package com.sesac.guseok_be.yh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<ParkEntity, Long> {
    List<ParkEntity> findByDistrict(String district);
}
