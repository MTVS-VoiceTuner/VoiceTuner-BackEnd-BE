package com.midnight.e5project.model.controller;

import com.midnight.e5project.model.config.WebClientUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestWebClientController {

    private final WebClientUtil webClientUtil;

    private static final String REQUEST_URL = "http://172.16.17.51:7777/ai";


    @GetMapping("/test-get")
    public String testGet() {
        return webClientUtil.getSync(REQUEST_URL, String.class);
    }

    @PostMapping("/edit_script")
    public String testPost(@RequestBody Object requestBody) {
        return webClientUtil.postSync(REQUEST_URL + "/edit_script", requestBody, String.class);
    }

    @PutMapping("/test-put")
    public String testPut(@RequestBody Object requestBody) {
        return webClientUtil.putSync(REQUEST_URL, requestBody, String.class);
    }

    @DeleteMapping("/test-delete")
    public String testDelete() {
        return webClientUtil.deleteSync(REQUEST_URL, String.class);
    }

    // JSON 형태로 변환한 문자열을 POST 요청으로 전송하는 메서드
    @PostMapping("/script")
    public String testPost() {
        // 문자열을 JSON 형식으로 변환
        Map<String, String> jsonData = new HashMap<>();
        jsonData.put("script", "스크린샷이란 컴퓨터 모니터 또는 스마트폰 등 전자기기 화면에 보이는 그대로를 찍은 출력 사진을 의미합니다." +
                "일반적인 사진과는 다르게 텍스트만 존재하는 이미지이거나, 이미지와 텍스트가 혼재되어 있는 경우가 많습니다." +
                "사람들이 스크린샷을 사용하는 이유는 특정 앱을 열거나 인터넷 검색 없이도 휴대폰의 갤러리에서 쉽게 접근할 수 있는 정보를" +
                "빠르게 저장할 수 있기 때문입니다. 그러나 다양한 정보가 통일성 없이 나열되어 있어 찾기 어려운 문제점이 있습니다.");

        // POST 요청을 보내고 결과를 반환
        return webClientUtil.postSync(REQUEST_URL, jsonData, String.class);
    }

}
