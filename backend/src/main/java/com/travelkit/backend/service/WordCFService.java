package com.travelkit.backend.service;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class WordCFService {
    public double measureSimilarity(String item, String otherUserItems) {
        try {
            // 모델 파일 경로 설정 (gensim에서 텍스트 형식으로 저장한 경로)
            String modelPath = "word2vec_model_korean.txt";         // 실제 경로로 변경
            // 모델 로드
            File modelFile = new File(modelPath);
            WordVectors wordVectors = WordVectorSerializer.loadStaticModel(modelFile);
            return wordVectors.similarity(item, otherUserItems);
        } catch (Exception e) {
            System.out.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}