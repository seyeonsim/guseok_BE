package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.domain.SmokingDTO;
import com.sesac.guseok_be.service.SmokingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/smoking/")
public class SmokingController {
    @Autowired
    private SmokingService smokingService;

    //모든 흡연 구역 데이터 호출, 존재하는 자치구 리스트 호출
    @GetMapping("/")
    public SmokingDTO home() {
        SmokingDTO response = new SmokingDTO();
        response.setDistricts(smokingService.getDistinctDistricts());
        List<Smoking> smokingAreas = smokingService.getAllData();
        response.setSmokingAreas(smokingAreas != null ? smokingAreas : Collections.emptyList());

        return response;
    }

    //자치구에 해당되는 데이터 호출
    @GetMapping("/district")
    public SmokingDTO getDistinctDistricts(@RequestParam(name = "districts", defaultValue = "default") String district) {
        SmokingDTO response = new SmokingDTO();
        List<Smoking> smokingAreas;
        if (district != "default") {
            smokingAreas = smokingService.getDataByDistrict(district);
        } else {
            smokingAreas = smokingService.getAllData();
        }
        response.setSmokingAreas(smokingAreas);
        response.setDistricts(smokingService.getDistinctDistricts());

        return response;
    }
}
