package com.backend.voicetuner.voicmodule.application.controller;

import com.backend.voicetuner.voicmodule.application.config.WebClientUtil;
import com.backend.voicetuner.voicmodule.application.dto.AudioDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientRequest;


import java.io.IOException;
import java.time.Duration;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class VoiceController {

    private final RestTemplate restTemplate = new RestTemplate();
    private MultipartFile file;

    private static final Logger log = LoggerFactory.getLogger(VoiceController.class);

    @Autowired
    private WebClientUtil webClientUtil;

    private final WebClient webClient;


    //    private static final String REQUEST_URL = "http://172.20.10.3:7777/ai";
    private static final String REQUEST_URL = "https://crappie-emerging-logically.ngrok-free.app";

    // 추가된 부분
    public VoiceController() {
        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create().responseTimeout(Duration.ofMinutes(3))))
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024)) // 메모리 사이즈 조정
                        .build())
                .build();
    }

    // response AI compare_similarity audio - 음성 파일 저장, 피드백 저장, 일치도 저장,
    // @PostMapping 어노테이션은 HTTP POST 요청을 해당 경로로 매핑합니다.

    // WebClient 설정: 응답 사이즈 제한 늘리기 및 타임아웃 설정

    // 한소절 전달
    @PostMapping("/sendVerse")
    public ResponseEntity<String> sendVerse(@RequestBody AudioDTO audioDTO) {

        if (audioDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client api request body is empty");
        }
        // ai server url
        String aiServerURL = "https://crappie-emerging-logically.ngrok-free.app";

        HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        HttpEntity<AudioDTO> requestEntity = new HttpEntity<>(audioDTO, headers);

        ResponseEntity<String> response;

        // 파일 전송
        try {
            // AI 서버로 파일 전송
            response = restTemplate.postForEntity(aiServerURL + "/vocal/upload-audio", requestEntity, String.class);

            System.out.println(response.getBody());
        } catch (RestClientException e) {
            System.out.println("!!! RestClientException : " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File sending failed.");
        }

        String responseBody = response.getBody();

        System.out.println("AI Server response body" + responseBody);
        if (responseBody == null || responseBody.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("AI Server Error");
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }


    // 한곡 전송

    @PostMapping("/sendSong")
    public String sendSong(@RequestPart() MultipartFile file) throws IOException {

        // 추가된 부분
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource()); // 파일 추가
        body.add("user_id", "123"); // 추가 데이터
        body.add("song_id", "song_456");
        body.add("track_id", "track_789");
        body.add("start_time", "0.0");
        body.add("end_time", "10.0");

        // 추가된 부분
        try {
            return webClient.method(HttpMethod.POST)
                    .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-audio")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(body))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (WebClientResponseException e) {
            return "Error: " + e.getResponseBodyAsString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    // wav 파일로 한 소절 보내기
    @Operation(summary = "wav 한 소절 전송", description = "이거 테스트 해야함")
    @PostMapping(value = "/sendOriginVerse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String testVerse(
            @RequestHeader("content-type") String contentType,
            @RequestHeader("content-length") String contentLength,
            @RequestPart("audio_file") MultipartFile file
        ) throws IOException {

        System.out.printf("contentType:%s%n", contentType);
        System.out.printf("contentLength:%s%n", contentLength);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());

        return webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })

                .retrieve()
                .bodyToMono(String.class)
                .block();

    }



    // wav 파일로 전체 곡 보내기
    // @RequestHeader("content-type") String contentType,
    // @RequestHeader("content-length") String contentLength
    @Operation(summary = "wav 전체 곡 전송", description = "이거 테스트 해야함")
    @PostMapping(value = "/sendOriginSong", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testSong(
            @RequestHeader("content-type") String contentType,
            @RequestHeader("content-length") String contentLength,
            @RequestPart("audio_file") MultipartFile file
    ) throws IOException {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());



        return webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })

                .retrieve()
                .bodyToMono(String.class)
                .block();

    }

    // 원본 파일 전송
    /*
    @PostMapping("/sendOriginSong")
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
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-audio") // 요청할 URI를 설정합니다.
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
    */

}