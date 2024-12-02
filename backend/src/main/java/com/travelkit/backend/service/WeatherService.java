package com.travelkit.backend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.travelkit.backend.Repository.WeatherRepository;
import com.travelkit.backend.domain.Checklist;
import com.travelkit.backend.domain.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class WeatherService {

    @Value("${OpenAi.api.key}")
    private final String API_KEY = "943e43b39b995ed1261253dc2e7dc594";  // 발급받은 API key

    @Autowired
    private WeatherRepository weatherRepository; // Weather 엔터티 저장소

    public List<Weather> generateWeatherData(Checklist checklist) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        CityService cityService = new CityService();

        LocalDate departureDate = checklist.getDepartureDate();
        LocalDate arrivalDate = checklist.getArrivalDate();
        String city = checklist.getDestination().getCity();

        int daysBetween = departureDate.until(arrivalDate).getDays() + 1;
        Optional<Map<String, Double>> coordinates = cityService.getCoordinatesByCity(city);
        double latitude;
        double longitude;

        if (coordinates.isPresent()) {
            latitude = coordinates.get().get("latitude");
            longitude = coordinates.get().get("longitude");
        }
        else {
            throw new IllegalArgumentException("City not found in database.");
        }

        // OpenWeatherMap API 호출 URL 구성
        String apiKey = "YOUR_API_KEY";
        String BASE_URL = "https://api.openweathermap.org/data/2.5/forecast/daily";
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("lat", latitude)
                .queryParam("lon", longitude)
                .queryParam("cnt", 16)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .toUriString();

        // API 호출 및 응답 처리
        String response = restTemplate.getForObject(url, String.class);
        List<Weather> weatherList = parseAndFilterWeatherData(response, checklist, departureDate, daysBetween);
        weatherRepository.saveAll(weatherList);
        return weatherList;
    }

    private List<Weather> parseAndFilterWeatherData(String jsonResponse, Checklist checklist, LocalDate departureDate, int daysBetween) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Weather> weatherList = new ArrayList<>();

        try {
            // JSON 파싱
            JsonNode rootNode = objectMapper.readTree(jsonResponse).path("list");

            int dayIndex = 0;
            for (JsonNode dayNode : rootNode) {
                // 날짜 계산
                LocalDate date = departureDate.plusDays(dayIndex);

                // departureDate와 arrivalDate 범위를 벗어나면 무시
                if (dayIndex >= daysBetween) break;

                // Weather 엔터티 생성
                Weather weather = new Weather();
                weather.setChecklist(checklist);
                weather.setLocalDate(date);
                weather.setTemp(dayNode.path("temp").path("day").asInt());
                weather.setMainWeather(dayNode.path("weather").get(0).path("main").asText());

                weatherList.add(weather);
                dayIndex++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherList;
    }
    public List<Weather> getWeatherByChecklistId(Long checklistId) {
        return weatherRepository.findByChecklistId(checklistId);
    }
}
