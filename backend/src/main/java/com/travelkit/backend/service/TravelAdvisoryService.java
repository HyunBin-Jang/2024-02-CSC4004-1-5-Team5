
package com.travelkit.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class TravelAdvisoryService {

    private final String BASE_URL = "https://www.travel-advisory.info/api";  // 기본 API URL

    public Map<String, Object> getAdvisoryByCountryCode(String countryCode) {
        RestTemplate restTemplate = new RestTemplate();

        // URL 구성: countrycode 파라미터를 포함하여 특정 국가의 정보를 요청
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("countrycode", countryCode)  // ISO 3166-1 alpha-2 국가 코드
                .toUriString();

        // API 호출 및 응답 받기
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // 응답 데이터 반환
        return response;
    }
}
