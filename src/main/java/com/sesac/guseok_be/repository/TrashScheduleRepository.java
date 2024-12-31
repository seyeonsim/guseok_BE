package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.TrashSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrashScheduleRepository extends JpaRepository<TrashSchedule, Number> {
    @Query("SELECT DISTINCT district FROM TrashSchedule ORDER BY district ASC")
    List<String> findDistinctDistricts();

    @Query("SELECT t FROM TrashSchedule t WHERE (:district IS NULL OR t.district = :district)")
    List<TrashSchedule> findDataByDistrict(@Param("district") String district);
}
