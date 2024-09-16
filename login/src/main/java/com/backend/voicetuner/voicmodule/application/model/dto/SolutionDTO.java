package com.backend.voicetuner.voicmodule.application.model.dto;

public class SolutionDTO {
    private int beatScore;
    private int pitchScore;
    private String vocalRange;
    // similarity to original song
    private int totalScore;

    public SolutionDTO() {}

    public SolutionDTO(int beatScore, int pitchScore, String vocalRange, int totalScore) {
        this.beatScore = beatScore;
        this.pitchScore = pitchScore;
        this.vocalRange = vocalRange;
        this.totalScore = totalScore;
    }

    public int getBeatScore() {
        return beatScore;
    }

    public void setBeatScore(int beatScore) {
        this.beatScore = beatScore;
    }

    public int getPitchScore() {
        return pitchScore;
    }

    public void setPitchScore(int pitchScore) {
        this.pitchScore = pitchScore;
    }

    public String getVocalRange() {
        return vocalRange;
    }

    public void setVocalRange(String vocalRange) {
        this.vocalRange = vocalRange;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "AIResponseDTO{" +
                "beatScore=" + beatScore +
                ", pitchScore=" + pitchScore +
                ", vocalRange='" + vocalRange + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}
