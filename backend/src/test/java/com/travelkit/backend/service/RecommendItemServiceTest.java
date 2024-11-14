package com.travelkit.backend.service;

import org.junit.jupiter.api.Test;

public class RecommendItemServiceTest {
    RecommendItemService recommendItemService = new RecommendItemService();

    @Test
    public void getOpenaiApiResponse(){
        String response = recommendItemService.getPreparationRecommendation("Tokyo, Japan", "clear sky", 15.1);
        System.out.println(response);
    }
}
