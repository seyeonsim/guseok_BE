package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.entity.ParkEntity;
import com.sesac.guseok_be.service.ParkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/park")
@RequiredArgsConstructor
public class ParkController {

    private final ParkService parkService;

    // 모든 공원 데이터 조회
    @GetMapping
    public List<ParkEntity> getAllParks() {
        return parkService.getAllParks();
    }

    // 특정 지역의 공원 데이터 조회
    @GetMapping("/by-district")
    public List<ParkEntity> getParksByDistrict(@RequestParam String district) {
        return parkService.getParksByDistrict(district);
    }

    // 특정 ID의 공원 데이터 조회
    @GetMapping("/{id}")
    public ResponseEntity<ParkEntity> getParkById(@PathVariable("id") Long id) { // "id" 명시
        return parkService.findParkById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/list")
    public  List<ParkEntity> getMainPark(@RequestParam String district, @RequestParam(required = false) Integer limit) {
        return parkService.findMainPark(district, limit);
    }
}
