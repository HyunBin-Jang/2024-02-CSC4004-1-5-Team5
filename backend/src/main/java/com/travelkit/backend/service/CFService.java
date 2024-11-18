package com.travelkit.backend.service;

import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Item;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CFService {
    public  Map<String, Double> recommendItems(String destination, Long checklistId, List<Checklist> checklists) {
        // 사용자별 준비물 매트릭스 생성
        Map<Long, Set<String>> userItemsMap = new HashMap<>();
        for (Checklist checklist : checklists) {
            Set<String> items = Optional.ofNullable(checklist.getChecklistItems())
                    .orElse(Collections.emptyList())
                    .stream()
                    .map(Item::getName)
                    .collect(Collectors.toSet());
            userItemsMap.put(checklist.getId(), items);
        }

        // 현재 사용자의 준비물 가져오기
        Set<String> currentUserItems = userItemsMap.getOrDefault(checklistId, new HashSet<>());

        // 사용자 간 유사도 계산
        Map<Long, Double> similarityMap = new HashMap<>();
        for (Map.Entry<Long, Set<String>> entry : userItemsMap.entrySet()) {
            Long otherChecklistId = entry.getKey();
            if (!otherChecklistId.equals(checklistId)) {
                double similarity = calculateCosineSimilarity(currentUserItems, entry.getValue());
                similarityMap.put(otherChecklistId, similarity);
            }
        }

        // 유사도 기반 추천 준비물 계산
        Map<String, Double> recommendationScores = new HashMap<>();
        for (Map.Entry<Long, Double> entry : similarityMap.entrySet()) {
            Long otherChecklistId = entry.getKey();
            Double similarity = entry.getValue();

            // 유사 사용자가 추가한 준비물 중 현재 사용자가 추가하지 않은 것만 추천
            Set<String> otherUserItems = userItemsMap.get(otherChecklistId);
            for (String item : otherUserItems) {
                if (!currentUserItems.contains(item)) {
                    recommendationScores.put(item, recommendationScores.getOrDefault(item, 0.0) + similarity);
                }
            }
        }

        // 추천 준비물 리스트 정렬
        return recommendationScores;
    }

    private double calculateCosineSimilarity(Set<String> itemsA, Set<String> itemsB) {
        // 교집합 크기 계산
        long intersectionSize = itemsA.stream().filter(itemsB::contains).count();
        // 코사인 유사도 계산
        return (double) intersectionSize / Math.sqrt(itemsA.size() * itemsB.size());
    }
}
