package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.TrashSchedule;
import com.sesac.guseok_be.service.TrashScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trash/")
public class TrashScheduleController {
    @Autowired
    private TrashScheduleService trashScheduleService;

    @GetMapping("/")
    public Map<String, Object> getAllTrashSchedule() {
        Map<String, Object> response = new HashMap<>();
        List<TrashSchedule> trashSchedules = trashScheduleService.getAllTrashSchedule();
        List<String> districts = trashScheduleService.getDistricts();

        response.put("trashSchedules", trashSchedules);
        response.put("districts", districts);

        return response;
    }

    @GetMapping("/district")
    public Map<String, Object> getTrashSchedule(@RequestParam(name = "districts", defaultValue = "default") String district) {
        Map<String, Object> response = new HashMap<>();
        List<TrashSchedule> trashSchedules = trashScheduleService.getSchedulesByDistrict(district);
        List<String> districts = trashScheduleService.getDistricts();

        response.put("trashSchedules", trashSchedules);
        response.put("districts", districts);

        return response;
    }
}
