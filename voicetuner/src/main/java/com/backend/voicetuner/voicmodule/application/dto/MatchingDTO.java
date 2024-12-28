package com.backend.voicetuner.voicmodule.application.dto;

public class MatchingDTO {

    private Long player1;
    private Long player2;
    private Long player3;
    private Long player4;

    public MatchingDTO() {}

    public MatchingDTO(Long player1, Long player2, Long player3, Long player4) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }

    public Long getPlayer1() {
        return player1;
    }

    public void setPlayer1(Long player1) {
        this.player1 = player1;
    }

    public Long getPlayer2() {
        return player2;
    }

    public void setPlayer2(Long player2) {
        this.player2 = player2;
    }

    public Long getPlayer3() {
        return player3;
    }

    public void setPlayer3(Long player3) {
        this.player3 = player3;
    }

    public Long getPlayer4() {
        return player4;
    }

    public void setPlayer4(Long player4) {
        this.player4 = player4;
    }

    @Override
    public String toString() {
        return "MatchingDTO{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", player3=" + player3 +
                ", player4=" + player4 +
                '}';
    }
}
