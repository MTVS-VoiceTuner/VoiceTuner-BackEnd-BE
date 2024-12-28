package com.backend.voicetuner.voicmodule.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;

public class SolutionDTO {
    @JsonProperty("user_id")
    private Long userId;
    @JsonProperty("song_id")
    private Long songId;
    @JsonProperty("track_id")
    private Long trackId;
    @JsonProperty("ai_answer")
    private String aiAnswer;
    @JsonProperty("ai_short_answer")
    private String aiShortAnswer;
    @JsonProperty("tempo_score")
    private float tempoScore;
    @JsonProperty("pitch_score")
    private float pitchScore;
    @JsonProperty("user_vocal_range")
    private String userVocalRange;
    @JsonProperty("final_score")
    private float finalScore;

    public SolutionDTO() {}

    public SolutionDTO(
            Long userId,
            Long songId,
            Long trackId,
            String aiAnswer,
            String aiShortAnswer,
            float tempoScore,
            float pitchScore,
            String userVocalRange,
            float finalScore
        ) {
            this.userId = userId;
            this.songId = songId;
            this.trackId = trackId;
            this.aiAnswer = aiAnswer;
            this.aiShortAnswer = aiShortAnswer;
            this.tempoScore = tempoScore;
            this.pitchScore = pitchScore;
            this.userVocalRange = userVocalRange;
            this.finalScore = finalScore;
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

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public String getAiAnswer() {
        return aiAnswer;
    }

    public void setAiAnswer(String aiAnswer) {
        this.aiAnswer = aiAnswer;
    }

    public String getAiShortAnswer() {
        return aiShortAnswer;
    }

    public void setAiShortAnswer(String aiShortAnswer) {
        this.aiShortAnswer = aiShortAnswer;
    }

    public float getTempoScore() {
        return tempoScore;
    }

    public void setTempoScore(float tempoScore) {
        this.tempoScore = tempoScore;
    }

    public float getPitchScore() {
        return pitchScore;
    }

    public void setPitchScore(float pitchScore) {
        this.pitchScore = pitchScore;
    }

    public String getUserVocalRange() {
        return userVocalRange;
    }

    public void setUserVocalRange(String userVocalRange) {
        this.userVocalRange = userVocalRange;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public String toString() {
        return "SolutionDTO{" +
                "userId=" + userId +
                ", songId=" + songId +
                ", trackId=" + trackId +
                ", aiAnswer='" + aiAnswer + '\'' +
                ", aiShortAnswer='" + aiShortAnswer + '\'' +
                ", tempoScore=" + tempoScore +
                ", pitchScore=" + pitchScore +
                ", userVocalRange='" + userVocalRange + '\'' +
                ", finalScore=" + finalScore +
                '}';
    }
}
