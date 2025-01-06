package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.entity.ParkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository // 작성자 : 박유현
public interface ParkRepository extends JpaRepository<ParkEntity, Long> {
    List<ParkEntity> findByDistrict(String district);
    List<ParkEntity> findByLikedUsersId(Long userId);
}
