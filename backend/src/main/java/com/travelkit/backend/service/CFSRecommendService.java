package com.travelkit.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CFSRecommendService {

    private final CFService cfService;

    @Autowired
    public CFSRecommendService(CFService cfService) {
        this.cfService = cfService;
    }

    public Map<String, Double> recommendItems(Long checklistId) {
        return cfService.recommendItems(checklistId);
    }
}
