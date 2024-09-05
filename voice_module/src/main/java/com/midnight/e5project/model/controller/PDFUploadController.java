package com.midnight.e5project.model.controller;

import com.midnight.e5project.model.dto.AudioDTO;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PDFUploadController {

    private final RestTemplate restTemplate = new RestTemplate();
    private MultipartFile file;

    @PostMapping("/upload")
    public List<String> handleFileUpload(@RequestParam("pdfFile") MultipartFile file) throws IOException {
        this.file = file;
        List<String> imagePaths = new ArrayList<>();

        if (!file.isEmpty()) {
            // PDF 파일을 임시 파일로 저장
            File tempFile = File.createTempFile("uploaded", ".pdf");
            file.transferTo(tempFile);

            // PDF를 이미지로 변환
            imagePaths = convertPDFToImages(tempFile);

            // 임시 파일 삭제
            tempFile.delete();

        }

        return imagePaths;  // 변환된 이미지 경로 반환
    }

    private List<String> convertPDFToImages(File pdfFile) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        try (PDDocument document = PDDocument.load(pdfFile)) {
            PDFRenderer renderer = new PDFRenderer(document);

            // 각 페이지를 이미지로 변환
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300);  // 300 DPI로 이미지 렌더링
                String imagePath = "slide_" + (i + 1) + ".png";
                File imageFile = new File(imagePath);
                ImageIO.write(image, "PNG", imageFile);
                imagePaths.add(imageFile.getAbsolutePath());
            }
        }

        return imagePaths;
    }

    @PostMapping("/sendBase64")
    public ResponseEntity<String> uploadWavFile(@RequestBody AudioDTO audioDTO) {
        if (audioDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client api request body is empty");
        }
        // ai server url
        String aiServerURL = "https://crappie-emerging-logically.ngrok-free.app";

        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
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