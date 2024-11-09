package com.travelkit.backend.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CityService {
    private List<Map<String, Object>> cityData;

    public CityService() throws IOException {
        loadCityData();
    }

    // JSON 파일을 읽어 cityData 리스트에 저장
    private void loadCityData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ClassPathResource resource = new ClassPathResource("cities.json");
        try (InputStream inputStream = resource.getInputStream()) {
            cityData = mapper.readValue(inputStream, new TypeReference<List<Map<String, Object>>>() {});
        }
    }

    // 도시 이름으로 위도와 경도를 가져오는 메서드
    public Optional<Map<String, Double>> getCoordinatesByCity(String cityName) {
        return cityData.stream()
                .filter(city -> city.get("city").equals(cityName))
                .map(city -> Map.of(
                        "latitude", (Double) city.get("latitude"),
                        "longitude", (Double) city.get("longitude")
                ))
                .findFirst();
    }
}
