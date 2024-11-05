package com.travelkit.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherService {
    @Value("${OpenWeather.api.key}")
    private final String API_KEY = "943e43b39b995ed1261253dc2e7dc594";  // 발급받은 API key
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    public Map<String, Object> getForecastByCoordinates(double latitude, double longitude) {
        RestTemplate restTemplate = new RestTemplate();

        // URL 구성
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")  // 섭씨 온도로 설정
                .toUriString();

        // API 호출 및 응답 받기
        Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);

        // 응답 데이터 반환
        return response;
    }
}
