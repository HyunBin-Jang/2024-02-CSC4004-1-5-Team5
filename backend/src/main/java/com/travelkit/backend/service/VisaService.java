package com.travelkit.backend.service;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class VisaService {

    private static final String VISA_JSON_PATH = "visa.json"; // 클래스패스 내의 JSON 파일 경로

    public boolean isVisaRequired(String country) {
        try {
            // ClassPathResource를 이용해 리소스 파일 접근
            ClassPathResource resource = new ClassPathResource(VISA_JSON_PATH);

            // InputStream으로 JSON 파일 읽기
            InputStream inputStream = resource.getInputStream();

            // JSON 데이터를 파싱하여 List<Map<String, Object>>로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> visaInfos = objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Map<String, Object>>>() {}
            );
            // 해당 국가의 비자 필요 여부 검색
            for (Map<String, Object> visaInfo : visaInfos) {
                String jsonCountry = (String) visaInfo.get("country");
                Boolean visaRequired = (Boolean) visaInfo.get("visaRequired");
                if (jsonCountry.equalsIgnoreCase(country)) {
                    return visaRequired != null ? visaRequired : false;
                }
            }
            return false; // 국가 정보가 없으면 기본값 false 반환
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("비자 정보 파일을 읽을 수 없습니다.", e);
        }
    }
}

