package com.travelkit.backend.service;

import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CFServiceTest {
    CFService cfService = new CFService();

    @Autowired
    ChecklistService checklistService;

    @Test
    public void CFTest(){
        System.out.println(cfService.recommendItems("Japan", 452L,checklistService.getAllChecklists()));
    }
}
