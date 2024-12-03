package com.travelkit.backend.controller;

import com.travelkit.backend.domain.Weather;
import com.travelkit.backend.service.ChecklistService;
import com.travelkit.backend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private ChecklistService checklistService;

    @GetMapping("/{checklistId}")
    public ResponseEntity<List<Weather>> fetchWeatherData(@PathVariable("checklistId") Long checklistId) throws IOException {
        List<Weather> weatherlist = weatherService.getWeatherByChecklistId(checklistId);
            return new ResponseEntity<>(weatherlist, HttpStatus.CREATED);
    }
}

