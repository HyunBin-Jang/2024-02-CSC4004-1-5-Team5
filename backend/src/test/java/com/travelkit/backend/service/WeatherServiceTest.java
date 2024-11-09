package com.travelkit.backend.service;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

public class WeatherServiceTest {
    WeatherService weatherService = new WeatherService();
    @Test
    public void requestWeather(){
        Map<String, Object> result =            // Seoul test
                weatherService.getForecastByCoordinates(37.5665, 126.9780);
        System.out.println(result);
    }
    @Test
    public void requestWeatherByCity() throws IOException {
        Map<String, Object> result =
                weatherService.getForecastByCity("Tokyo");
        System.out.println(result);
    }
}
