package com.backend.voicetuner.voicmodule.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VoiceDataDTO {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("song_id")
    private Long songId;

    public VoiceDataDTO() {}

    public VoiceDataDTO(Long userId, Long songId) {
        this.userId = userId;
        this.songId = songId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "VoiceDataDTO{" +
                "userId=" + userId +
                ", songId=" + songId +
                '}';
    }
}
