package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.NoSmoking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoSmokingRepository extends JpaRepository<NoSmoking, Long> {

    @Query("SELECT DISTINCT district FROM NoSmoking ORDER BY district ASC")
    List<String> findDistinctDistricts();

    @Query("SELECT s FROM NoSmoking s WHERE (:district IS NULL OR s.district = :district)")
    List<NoSmoking> findDataByDistrict(@Param("district") String district);

    @Query("SELECT s FROM NoSmoking s")
    List<NoSmoking> findAllData();
}
