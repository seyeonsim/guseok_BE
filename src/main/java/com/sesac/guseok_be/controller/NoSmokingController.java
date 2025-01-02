package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.NoSmoking;
import com.sesac.guseok_be.domain.SmokingDTO;
import com.sesac.guseok_be.service.NoSmokingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nosmoking/")
public class NoSmokingController {
    @Autowired
    private NoSmokingService noSmokingService;

    //전체 금연지역 및 존재하는 자치구 호출
    @GetMapping("/")
    public SmokingDTO Home() {
        SmokingDTO response = new SmokingDTO();
        List<String> districts = noSmokingService.getDistinctDistricts();
        List<NoSmoking> noSmokingAreas = noSmokingService.getAllData();

        response.setDistricts(districts);
        response.setNoSmokingAreas(noSmokingAreas);

        return response;
    }

    //특정 지역에 해당하는 데이터 호출
    @GetMapping("/district")
    public SmokingDTO District(@RequestParam(name = "districts", defaultValue = "default") String district) {
        SmokingDTO response = new SmokingDTO();
        List<String> districts = noSmokingService.getDistinctDistricts();
        List<NoSmoking> noSmokingAreas = noSmokingService.getDataByDistrict(district);

        response.setDistricts(districts);
        response.setNoSmokingAreas(noSmokingAreas);

        return response;
    }
}
