package com.sesac.guseok_be.service;

import com.sesac.guseok_be.domain.Smoking;

import java.util.List;

public interface SmokingService {

    List<String> getDistinctDistricts();

    List<Smoking> getDataByDistrict(String district);

    List<Smoking> getAllData();
}
