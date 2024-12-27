package com.sesac.guseok_be.yh;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/parks")
@RequiredArgsConstructor
public class ParkController {

    @Autowired
    private ParkService parkService;

    @GetMapping
    public List<ParkEntity> getAllParks() {
        return parkService.getAllParks();
    }

    @GetMapping("/by-district")
    public List<ParkEntity> getParksByDistrict(@RequestParam String district) {
        return parkService.getParksByDistrict(district);
    }
}

