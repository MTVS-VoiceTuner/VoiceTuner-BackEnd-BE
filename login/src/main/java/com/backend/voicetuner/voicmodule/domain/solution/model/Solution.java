package com.backend.voicetuner.voicmodule.domain.solution.model;

import jakarta.persistence.*;

@Entity
public class Solution {

    @Id
    @Column(name="SOLUTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long solutionId;

    @Column(name="USER_ID", nullable = false)
    public Long userId;

    @Column(name="SONG_ID", nullable = false)
    public Long songId;

    @Column(name="TRACK_ID")
    public Long trackId;

    @Column(name="AI_ANSWER", columnDefinition = "TEXT")
    public String aiAnswer;

    @Column(name="TEMPO_SCORE")
    public float tempoScore;

    @Column(name="PITCH_SCORE")
    public float pitchScore;

    @Column(name="USER_VOCAL_RANGE")
    public String userVocalRange;

    @Column(name="FINAL_SCORE")
    public float finalScore;

    public Solution() {}

    public Solution(
            Long userId,
            Long songId,
            Long trackId,
            String aiAnswer,
            float tempoScore,
            float pitchScore,
            String userVocalRange,
            float finalScore) {
        this.userId = userId;
        this.songId = songId;
        this.trackId = trackId;
        this.aiAnswer = aiAnswer;
        this.tempoScore = tempoScore;
        this.pitchScore = pitchScore;
        this.userVocalRange = userVocalRange;
        this.finalScore = finalScore;
    }

    public Long getSolutionId() {
        return solutionId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSongId() {
        return songId;
    }

    public Long getTrackId() {
        return trackId;
    }

    public String getAiAnswer() {
        return aiAnswer;
    }

    public float getTempoScore() {
        return tempoScore;
    }

    public float getPitchScore() {
        return pitchScore;
    }

    public String getUserVocalRange() {
        return userVocalRange;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }

    public void setAiAnswer(String aiAnswer) {
        this.aiAnswer = aiAnswer;
    }

    public void setTempoScore(float tempoScore) {
        this.tempoScore = tempoScore;
    }

    public void setPitchScore(float pitchScore) {
        this.pitchScore = pitchScore;
    }

    public void setUserVocalRange(String userVocalRange) {
        this.userVocalRange = userVocalRange;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionId=" + solutionId +
                ", userId=" + userId +
                ", songId=" + songId +
                ", trackId=" + trackId +
                ", aiAnswer='" + aiAnswer + '\'' +
                ", tempoScore=" + tempoScore +
                ", pitchScore=" + pitchScore +
                ", userVocalRange='" + userVocalRange + '\'' +
                ", finalScore=" + finalScore +
                '}';
    }
}


