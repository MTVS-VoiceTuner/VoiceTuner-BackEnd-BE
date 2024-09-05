package com.midnight.e5project.model.controller;

import com.midnight.e5project.model.config.WebClientUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class WebClientTest {

    @Autowired
    private WebClientUtil webClientUtil;

    private static final String REQUEST_URL = "http://192.168.1.45:7777/ai/edit_script"; // 실제 URL로 변경하세요

    @Test
    public void testPostSync() {
        // JSON 형식으로 변환할 데이터 준비
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("script", "스크린샷이란 컴퓨터 모니터 또는 스마트폰 등 전자기기 화면에 보이는 그대로를 찍은 출력 사진을 의미합니다." +
                "일반적인 사진과는 다르게 텍스트만 존재하는 이미지이거나, 이미지와 텍스트가 혼재되어 있는 경우가 많습니다." +
                "사람들이 스크린샷을 사용하는 이유는 특정 앱을 열거나 인터넷 검색 없이도 휴대폰의 갤러리에서 쉽게 접근할 수 있는 정보를" +
                "빠르게 저장할 수 있기 때문입니다. 그러나 다양한 정보가 통일성 없이 나열되어 있어 찾기 어려운 문제점이 있습니다.");

        // POST 요청을 보내고 응답 결과를 받아옴
        String response = webClientUtil.postSync(REQUEST_URL, jsonData, String.class);

        // 응답을 콘솔에 출력
        System.out.println("서버 응답: " + response);

        // 응답에 대한 간단한 테스트 (응답이 null이 아님을 확인)
        Assert.notNull(response, "응답이 null이어서는 안 됩니다.");
    }
}
