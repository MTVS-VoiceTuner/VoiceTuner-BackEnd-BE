package com.midnight.e5project;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PDFToPNG {
    public static void main(String[] args) {
        // PDF 파일 경로
        String pdfFilePath = "C:\\Lecture\\project";

        // PNG 이미지 저장 경로 리스트
        List<String> imagePaths = convertPDFToPNG(pdfFilePath);

        // 저장된 이미지 경로 출력
        for (String path : imagePaths) {
            System.out.println(path);
        }
    }

    public static List<String> convertPDFToPNG(String pdfPath) {
        List<String> paths = new ArrayList<>();

        try (PDDocument document = PDDocument.load(new File(pdfPath))) {
            PDFRenderer renderer = new PDFRenderer(document);

            // 각 페이지를 이미지로 변환
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = renderer.renderImageWithDPI(i, 300);
                String imagePath = "slide_" + (i + 1) + ".png";
                ImageIO.write(image, "PNG", new File(imagePath));
                paths.add(imagePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return paths;
    }
}