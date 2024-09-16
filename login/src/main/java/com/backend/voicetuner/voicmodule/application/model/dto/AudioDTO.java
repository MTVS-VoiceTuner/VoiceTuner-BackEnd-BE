package com.backend.voicetuner.voicmodule.application.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AudioDTO {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("song_id")
    private String songId;

    @JsonProperty("track_id")
    private String trackId;

    @JsonProperty("start_time")
    private float startTime;

    @JsonProperty("end_time")
    private float endTime;

    @JsonProperty("audio_data")
    private String audioData;

    public AudioDTO() {}

    public AudioDTO(String userId, String songId, String trackId, float startTime, float endTime, String audioData) {
        this.userId = userId;
        this.songId = songId;
        this.trackId = trackId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.audioData = audioData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public float getStartTime() {
        return startTime;
    }

    public void setStartTime(float startTime) {
        this.startTime = startTime;
    }

    public float getEndTime() {
        return endTime;
    }

    public void setEndTime(float endTime) {
        this.endTime = endTime;
    }

    public String getAudioData() {
        return audioData;
    }

    public void setAudioData(String audioData) {
        this.audioData = audioData;
    }

    @Override
    public String toString() {
        return "AudioDTO{" +
                "userId='" + userId + '\'' +
                ", songId='" + songId + '\'' +
                ", trackId='" + trackId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", audioData='" + audioData + '\'' +
                '}';
    }
}
