package com.backend.voicetuner.voicmodule.application.controller;

import com.backend.voicetuner.voicmodule.application.config.WebClientUtil;
import com.backend.voicetuner.voicmodule.application.dto.AudioDTO;
import com.backend.voicetuner.voicmodule.application.dto.SolutionDTO;
import com.backend.voicetuner.voicmodule.application.dto.VoiceDataDTO;
import com.backend.voicetuner.voicmodule.domain.solution.service.SolutionService;
import com.nimbusds.jose.shaded.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
import reactor.core.publisher.Mono;
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
    private SolutionService solutionService;

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

    /**
     * wav 파일로 한 소절 보내기(저장 X) - sendOriginVerse
     */

    @Operation(summary = "wav 한 소절 전송", description = "이거 테스트 해야함")
    @PostMapping(value = "/sendOriginVerse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String testVerse(
            @RequestHeader("content-type") String contentType,
            @RequestHeader("content-length") String contentLength,
            @RequestPart("audio_file") MultipartFile file
    ) throws IOException, ParseException {

        System.out.printf("contentType:%s%n", contentType);
        System.out.printf("contentLength:%s%n", contentLength);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());


/*         /upload-wav-feedback // 피드백 전송 uri
        return webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })

                .retrieve()
                .bodyToMono(String.class)
                .block();*/

        return webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
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

    /**
     * wav 파일로 전체 곡 전송(저장 o) - sendSaveSolution
     */

    @Operation(summary = "wav 한 소절 전송", description = "이거 테스트 해야함")
    @PostMapping(value = "/sendSaveSolution", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SolutionDTO saveSolution(
            @RequestHeader("content-type") String contentType,
            @RequestHeader("content-length") String contentLength,
            @RequestPart("audio_file") MultipartFile file
    ) throws IOException, ParseException {

        // 확인용 로그
        System.out.printf("contentType:%s%n", contentType);
        System.out.printf("contentLength:%s%n", contentLength);

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());

/*         /upload-wav-feedback // 피드백 전송 uri
        return webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })

                .retrieve()
                .bodyToMono(String.class)
                .block();*/

        // 결과지 SolutionDTO에 저장
        SolutionDTO result = webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })
                .retrieve()
                .bodyToMono(SolutionDTO.class)
                .block();

        // 확인용
        System.out.println(result);

        // 결과 저장 - update -> record

        solutionService.recordSolution(result);

        return result;
    }


    /**
     * wav 한 소절 곡 전송 - /sendOriginSong
     * => "/sendSaveSolution"로 교체
     */

    // @RequestHeader("content-type") String contentType,
    // @RequestHeader("content-length") String contentLength
    @Operation(summary = "wav 한 소절 곡 전송", description = "이거 테스트 해야함")
    @PostMapping(value = "/sendOriginSong", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testSong(
            @RequestHeader("content-type") String contentType,
            @RequestHeader("content-length") String contentLength,
            @RequestPart("audio_file") MultipartFile file
    ) throws IOException {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());


        try {
            return webClient.method(HttpMethod.POST)
                    .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(BodyInserters.fromMultipartData(builder.build()))
                    .httpRequest(request -> {
                        HttpClientRequest reactorRequest = request.getNativeRequest();
                        reactorRequest.responseTimeout(Duration.ofDays(3));  // 타임아웃 3일 설정
                    })
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(), clientResponse -> {
                        // 응답이 4xx 또는 5xx 에러일 경우, 에러 로그 출력
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    log.error("Error Status: {}", clientResponse.statusCode());
                                    log.error("Error Body: {}", errorBody);
                                    return Mono.error(new RuntimeException("Failed to upload audio: " + errorBody));
                                });
                    })
                    .bodyToMono(String.class)
                    .block();

        } catch (WebClientResponseException e) {
            log.error("WebClientResponseException: {}", e.getResponseBodyAsString());
            return "WebClient Error: " + e.getResponseBodyAsString();
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }

    /**
     * 솔루션 저장 api
     */

    @Operation(summary = "솔루션 저장", description = "이거 테스트 해야함")
    @PostMapping(value = "/saveSolutionTest", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public SolutionDTO saveSolutionTest(
            @RequestPart("info") VoiceDataDTO voiceData,
            @RequestPart("audio-file") MultipartFile file
    ) throws IOException, ParseException {

        // 확인용 로그
        System.out.printf("userId:%s%n", voiceData.getUserId());
        System.out.printf("songId:%s%n", voiceData.getSongId());

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("audio_file", file.getResource());

        // 결과지 SolutionDTO에 저장
        SolutionDTO result = webClient.method(HttpMethod.POST)
                .uri("https://crappie-emerging-logically.ngrok-free.app/vocal/upload-wav-feedback")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .httpRequest(request -> {

                    HttpClientRequest reactorRequest = request.getNativeRequest();
                    reactorRequest.responseTimeout(Duration.ofDays(3));
                })
                .retrieve()
                .bodyToMono(SolutionDTO.class)
                .block();


        // 확인용
        System.out.println(result);

        result.setUserId(voiceData.getUserId());
        result.setSongId(voiceData.getSongId());

        // 결과 저장 - update -> record

        solutionService.recordSolution(result);

        return result;
    }

/**
인코딩 방식
 */
/*

    // 인코딩 후 한 소절 전송
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

    // 인코딩 후 한곡 전송
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
*/

}