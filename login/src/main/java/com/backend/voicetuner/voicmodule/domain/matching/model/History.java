package com.backend.voicetuner.voicmodule.domain.matching.model;

import jakarta.persistence.*;

@Entity
public class History {

    @Id
    @Column(name = "MATCHING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @Column(name = "PLAYER_1")
    private Long player1;

    @Column(name = "PLAYER_2")
    private Long player2;

    @Column(name = "PLAYER_3")
    private Long player3;

    @Column(name = "PLAYER_4")
    private Long player4;

    public History() {}

    public History(Long player1, Long player2, Long player3, Long player4) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }

    public Long getMatchingId() {
        return matchingId;
    }

    public Long getPlayer1() {
        return player1;
    }

    public Long getPlayer2() {
        return player2;
    }

    public Long getPlayer3() {
        return player3;
    }

    public Long getPlayer4() {
        return player4;
    }

    @Override
    public String toString() {
        return "History{" +
                "matchingId=" + matchingId +
                ", player1=" + player1 +
                ", player2=" + player2 +
                ", player3=" + player3 +
                ", player4=" + player4 +
                '}';
    }
}
