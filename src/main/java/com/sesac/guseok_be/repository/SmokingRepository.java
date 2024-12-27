package com.sesac.guseok_be.repository;

import com.sesac.guseok_be.domain.Smoking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmokingRepository extends JpaRepository<Smoking, Number> {

    @Query("SELECT DISTINCT district FROM Smoking")
    List<String> findDistinctDistricts();

    @Query("SELECT s FROM Smoking s WHERE (:district IS NULL OR s.district = :district)")
    List<Smoking> findDataByDistrict(@Param("district") String district);

    @Query("SELECT address FROM Smoking WHERE (:address IS NULL OR address = :address)")
    List<String> findAddressesByAddress(@Param("address") String address);

    @Query("SELECT s FROM Smoking s")
    List<Smoking> findAllData();
}
