package com.backend.voicetuner.voicmodule.model.controller;

import com.backend.voicetuner.voicmodule.model.dto.AudioDTO;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class PDFUploadController {

    private final RestTemplate restTemplate = new RestTemplate();
    private MultipartFile file;

    @PostMapping("/sendBase64")
    public ResponseEntity<String> uploadWavFile(@RequestBody AudioDTO audioDTO) {
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
}