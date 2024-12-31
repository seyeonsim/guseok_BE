package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.service.SmokingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/smoking/")
public class SmokingController {
    @Autowired
    private SmokingService smokingService;

    @GetMapping("/")
    public Map<String, Object> home() {
        Map<String, Object> response = new HashMap<>();
        response.put("districts", smokingService.getDistinctDistricts());
        List<Smoking> smokingAreas = smokingService.getAllData();
        response.put("smokingAreas", smokingAreas != null ? smokingAreas : Collections.emptyList());

        return response;
    }

    @GetMapping("/district")
    public Map<String, Object> getDistinctDistricts(@RequestParam(name = "districts", defaultValue = "default") String district) {
        Map<String, Object> response = new HashMap<>();
        List<Smoking> smokingAreas;
        if (district != "default") {
            smokingAreas = smokingService.getDataByDistrict(district);
        } else {
            smokingAreas = smokingService.getAllData();
        }
        response.put("smokingAreas", smokingAreas);
        response.put("districts", smokingService.getDistinctDistricts());

        return response;
    }
}
