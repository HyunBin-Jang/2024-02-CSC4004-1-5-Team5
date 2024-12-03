package com.travelkit.backend.service;

import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Weather;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecommendItemService {

    @Autowired
    WeatherService weatherService;

    @Value("${OpenAi.api.key}")
    private String OPENAI_API_KEY;
    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getPreparationRecommendation(Checklist checklist) {
        String location = checklist.getDestination().getCountry();
        LocalDate departureDate = checklist.getDepartureDate();
        LocalDate arrivalDate = checklist.getArrivalDate();
        int duration = departureDate.until(arrivalDate).getDays() + 1;
        List<Weather> weathers = weatherService.getWeatherByChecklistId(checklist.getId());


        // 1. temp 값 추출 및 연결
        String tempsString = weathers.stream()
                .map(weather -> String.valueOf(weather.getTemp())) // int -> String 변환
                .collect(Collectors.joining(", ")); // 쉼표와 공백으로 연결

        // 2. mainWeather 값 추출 및 연결
        String mainWeathersString = weathers.stream()
                .map(Weather::getMainWeather) // Weather 객체에서 mainWeather 추출
                .collect(Collectors.joining(", ")); // 쉼표와 공백으로 연결

        String prompt = String.format(
                "%s으로 %d일간 여행을 갑니다. 일간 날씨는 각각 %s이고 일간 기온은 각각 %s°C입니다." +
                        "해당 나라의 문화를 고려하여 사람들이 놓칠만한 준비물을 7개 추천해 주세요. 단답형으로 쉼표로 구분해주세요",
                location, duration, mainWeathersString, tempsString
        );

        // HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + OPENAI_API_KEY);
        headers.set("Content-Type", "application/json");

        // 요청 본문 설정 (gpt-4-turbo)
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4-turbo");
        requestBody.put("messages", List.of(message));
        requestBody.put("max_tokens", 200);
        requestBody.put("temperature", 0.8);    // 다양성 증가를 위한 temperature 설정
        requestBody.put("top_p", 0.9);      // 상위 90% 확률의 선택지에서만 샘플링
        requestBody.put("frequency_penalty", 0.5);  // 반복 방지
        requestBody.put("presence_penalty", 0.6);       // 새로운 내용 유도

        // 요청 본문을 JSON 형식으로 변환
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        // OpenAI API로 요청
        ResponseEntity<String> response = restTemplate.exchange(
                OPENAI_API_URL, HttpMethod.POST, request, String.class
        );

        // 응답 파싱
        JSONObject jsonResponse = new JSONObject(response.getBody());
        String recommendedItems = jsonResponse
                .getJSONArray("choices")
                .getJSONObject(0)
                .getJSONObject("message")
                .getString("content")
                .trim();
        List<String> itemList = Arrays.asList(recommendedItems.split(",\\s*")); // 쉼표와 공백을 기준으로 나눔
        return itemList;
    }
}
