package com.travelkit.backend.controller;

import com.travelkit.backend.service.CFSRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class RecommendService {
    private final CFSRecommendService cfsRecommendService;

    public RecommendService(CFSRecommendService cfsRecommendService) {
        this.cfsRecommendService = cfsRecommendService;
    }
    @PostMapping("/recommend/{checklistId}")
    public ResponseEntity<Map<String, Double>> getRecommendations(
            @PathVariable("checklistId")  Long checklistId) {
        Map<String, Double> recommendations = cfsRecommendService.recommendItems(checklistId);
        return ResponseEntity.ok(recommendations);
    }
}
