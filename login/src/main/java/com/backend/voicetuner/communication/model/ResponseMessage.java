package com.backend.voicetuner.communication.model;

import lombok.*;

import java.util.Map;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage {

    private int httpStatus;
    private String message;
    private Map<String, Object> results;

}
