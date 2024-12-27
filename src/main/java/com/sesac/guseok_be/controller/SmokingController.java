package com.sesac.guseok_be.controller;

import com.sesac.guseok_be.domain.Smoking;
import com.sesac.guseok_be.service.SmokingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/smoking/*")
public class SmokingController {
    @Autowired
    private SmokingService smokingService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("districts", smokingService.getDistinctDistricts());
        List<Smoking> smokingAreas = smokingService.getAllData();
        model.addAttribute("smokingAreas", smokingAreas != null ? smokingAreas : Collections.emptyList());
        return "index";
    }

    @GetMapping("/district")
    public String getDistinctDistricts(@RequestParam(name = "districts", defaultValue = "default") String district, Model model) {
        List<Smoking> smokingAreas;
        if (district != "default") {
            smokingAreas = smokingService.getDataByDistrict(district);
        } else {
            smokingAreas = smokingService.getAllData();
        }
        model.addAttribute("smokingAreas", smokingAreas);
        model.addAttribute("districts", smokingService.getDistinctDistricts());

        return "index";
    }

    @GetMapping("/reset")
    public String reset(Model model) {
        model.addAttribute("districts", smokingService.getDistinctDistricts());
        List<Smoking> smokingAreas = smokingService.getAllData();
        model.addAttribute("smokingAreas", smokingAreas != null ? smokingAreas : Collections.emptyList());
        return "redirect:/";
    }
}
