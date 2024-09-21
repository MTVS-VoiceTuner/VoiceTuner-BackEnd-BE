package com.backend.voicetuner.voicmodule.domain.solution.model;

import jakarta.persistence.*;

@Entity
public class Solution {

    @Id
    @Column(name="SOLUTION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long solutionId;

    @Column(name="USER_ID")
    public Long userId;

    @Column(name="SONG_ID")
    public Long songId;

    @Column(name="BEAT_SCORE")
    public int beatScore;

    @Column(name="PITCH_SCORE")
    public int pitchScore;

    @Column(name="VOCAL_RANGE")
    public String vocalRange;

    @Column(name="TOTAL_SCORE")
    public int totalScore;

    public Solution() {}

    public Solution(Long userId, Long songId, int beatScore, int pitchScore, String vocalRange, int totalScore) {
        this.userId = userId;
        this.songId = songId;
        this.beatScore = beatScore;
        this.pitchScore = pitchScore;
        this.vocalRange = vocalRange;
        this.totalScore = totalScore;
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

    public int getBeatScore() {
        return beatScore;
    }

    public int getPitchScore() {
        return pitchScore;
    }

    public String getVocalRange() {
        return vocalRange;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public void setBeatScore(int beatScore) {
        this.beatScore = beatScore;
    }

    public void setPitchScore(int pitchScore) {
        this.pitchScore = pitchScore;
    }

    public void setVocalRange(String vocalRange) {
        this.vocalRange = vocalRange;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "solutionId=" + solutionId +
                ", userId=" + userId +
                ", songId=" + songId +
                ", beatScore=" + beatScore +
                ", pitchScore=" + pitchScore +
                ", vocalRange='" + vocalRange + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}


