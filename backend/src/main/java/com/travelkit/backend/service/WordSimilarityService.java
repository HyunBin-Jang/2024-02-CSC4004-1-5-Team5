package com.travelkit.backend.service;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.stereotype.Service;


@Service
public class WordSimilarityService {
    public int calculateWordFormSimilarity(String item, String otherUserItems) {
        // 초성, 중성, 종성 문자열로 분리
        String decomposedItem = decomposeToJamo(item);
        String decomposedOtherUserItems = decomposeToJamo(otherUserItems);

        // LevenshteinDistance 객체 생성
        LevenshteinDistance levenshtein = new LevenshteinDistance();

        // 편집 거리 계산
        return levenshtein.apply(decomposedItem, decomposedOtherUserItems);
    }
    public String decomposeToJamo(String word) {
        StringBuilder result = new StringBuilder();
        for (char c : word.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7A3) { // 한글 유니코드 범위
                int base = c - 0xAC00;
                char chosung = CHOSUNG[base / (21 * 28)];
                char jungsung = JUNGSUNG[(base / 28) % 21];
                char jongsung = JONGSUNG[base % 28];

                // 결과에 초성, 중성, 종성 추가
                result.append(chosung).append(jungsung);
                if (jongsung != ' ') { // 종성이 있을 경우만 추가
                    result.append(jongsung);
                }
            } else {
                // 한글이 아닌 경우 그대로 추가
                result.append(c);
            }
        }
        return result.toString();
    }

    // 초성, 중성, 종성 배열 정의
    private static final char[] CHOSUNG = {'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
    private static final char[] JUNGSUNG = {'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ'};
    private static final char[] JONGSUNG = {' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'};
}
