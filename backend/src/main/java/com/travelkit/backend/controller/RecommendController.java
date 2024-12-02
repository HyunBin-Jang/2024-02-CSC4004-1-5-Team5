package com.travelkit.backend.controller;

import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.service.CFSRecommendService;
import com.travelkit.backend.service.ChecklistService;
import com.travelkit.backend.service.RecommendItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
public class RecommendController {

    private final CFSRecommendService cfsRecommendService;
    private final RecommendItemService recommendItemService;
    private final ChecklistService checklistService;
    @Autowired
    public RecommendController(CFSRecommendService cfsRecommendService, RecommendItemService recommendItemService, ChecklistService checklistService) {
        this.cfsRecommendService = cfsRecommendService;
        this.recommendItemService = recommendItemService;
        this.checklistService = checklistService;
    }

    @PostMapping("/recommend/{checklistId}")
    public ResponseEntity<Map<String, Double>> getRecommendation(
            @PathVariable("checklistId")  Long checklistId) {
        Map<String, Double> recommendations = cfsRecommendService.recommendItems(checklistId);
        return ResponseEntity.ok(recommendations);
    }

    @PostMapping("/recommend/openai/{checklistId}")
    public ResponseEntity<Object> getRecommendationsByOpenAi(
            @PathVariable("checklistId")  Long checklistId) {
        Optional<Checklist> checklist = checklistService.getChecklistById(checklistId);
        if(checklist.isPresent()) {
            List<String> recommendations = recommendItemService.getPreparationRecommendation(checklist.get());
            return ResponseEntity.ok(recommendations);
        }
        else {
            return new ResponseEntity<>("Checklist not found", HttpStatus.NOT_FOUND);
        }   
    }
}
