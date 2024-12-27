package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.entity.DistrictEntity;
import com.sesac.guseok_be.service.DistrictService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
@RestController
public class DistrictController {

    @Autowired
    DistrictService districtService;

    @GetMapping("/districts")
    public List<DistrictEntity> getDistricts() {
     return districtService.getDistricts();
    }
}
