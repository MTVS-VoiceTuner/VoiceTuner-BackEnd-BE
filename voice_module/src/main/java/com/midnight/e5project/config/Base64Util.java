package com.midnight.e5project.config;

import java.util.Base64;

/**
 * Base64 트렌스코더
 * 인코딩 : 파일을 byte 단위의 배열로 바꾸는 것
 * 디코딩 : byte 배열을 원본 파일로 되돌리는 것
 */


// 'Base64Util' 라는 유틸리티 클래스를 정의한다.
// Base64로 인코딩도니 문자열을 디코딩하여 바이트 배열로 변환하는 역할을 한다.
public class Base64Util {

    public static byte[] decodeBase64(String base64String) {
        // decode() : java 의 'java.util.Base64' 라이브러리를 사요앟여 Base64 인코딩된 문자열을 바이트 배열로 디코딩하는 작업을 수행한다.
        return Base64.getDecoder().decode(base64String);
    }

    public static String encodeBase64(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
}
