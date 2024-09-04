package com.backend.voicetuner.communication.model.controller;

import com.backend.voicetuner.communication.model.config.WebClientUtil;
import com.backend.voicetuner.communication.model.service.PPTService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClientRequest;

import java.io.IOException;
import java.time.Duration;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ppt")
public class PPTController {

    private static final Logger log = LoggerFactory.getLogger(PPTController.class);
    @Autowired
    private WebClientUtil webClientUtil;

    private final WebClient webClient;

    @Autowired
    private PPTService pptService;

//    private static final String REQUEST_URL = "http://172.20.10.3:7777/ai";
    private static final String REQUEST_URL = "http://172.16.17.132:7777/ai";


    // response AI edit script - 원본 script 저장, 수정 스크립트 저장, 
    @PostMapping("/edit_script")
    public String processScript(@RequestBody Object requestBody) {

        log.info("Received script: {}", requestBody);

        // 원본 대사 저장
        pptService.registOriginScript(requestBody.toString());

        // 수정한 대사 저장

        String result = webClientUtil.postSync(REQUEST_URL + "/edit_script", requestBody, String.class);
        pptService.registProcessScript(result);
        return result;
    }


    // response AI compare_similarity audio - 음성 파일 저장, 피드백 저장, 일치도 저장,
    // @PostMapping 어노테이션은 HTTP POST 요청을 해당 경로로 매핑합니다.
    @PostMapping("/compare_similarity")
    public String testPost(
            // @RequestPart 어노테이션은 요청의 일부인 멀티파트 파일을 매개변수로 받습니다.
            @RequestPart() MultipartFile file,
            // @RequestHeader 어노테이션은 요청 헤더의 값을 매개변수로 받습니다.
            @RequestHeader("content-type") String contentType,
            // @RequestHeader 어노테이션은 요청 헤더의 값을 매개변수로 받습니다.
            @RequestHeader("content-length") String contentLength) throws IOException {

        // 로그 정보를 기록합니다. 파일의 컨텐트 타입과 길이를 로그로 출력합니다.
        log.info("contentType:{}", contentType);
        log.info("contentLength:{}", contentLength);

        // MultipartBodyBuilder를 사용하여 멀티파트 요청의 본문을 구성합니다.
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        // 파일 데이터를 'audio_file' 파트로 추가합니다.
        builder.part("audio_file", file.getResource());

        // WebClient를 사용하여 POST 요청을 외부 서버로 보냅니다.
        return webClient.method(HttpMethod.POST) // HTTP 메소드를 POST로 설정합니다.
                .uri(REQUEST_URL + "/compare_similarity") // 요청할 URI를 설정합니다.
                .contentType(MediaType.MULTIPART_FORM_DATA) // 컨텐트 타입을 멀티파트 폼 데이터로 설정합니다.
                .body(BodyInserters.fromMultipartData(builder.build())) // 멀티파트 데이터 본문을 설정합니다.
                .httpRequest(request -> {
                    // 요청 객체를 가져와 추가 설정을 합니다. 여기서는 응답 타임아웃을 설정합니다.
                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3)); // 응답 타임아웃을 3일로 설정합니다.
                })
                .retrieve() // 응답을 검색합니다.
                .bodyToMono(String.class) // 응답 본문을 String 타입으로 변환합니다.
                .block(); // 비동기 작업이 완료될 때까지 현재 스레드를 차단합니다.
    }


}