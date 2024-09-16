package com.backend.voicetuner.record.matching.model;

import jakarta.persistence.*;

@Entity
public class History {

    @Id
    @Column(name = "MATCHING_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchingId;

    @Column(name = "PLAYER_1")
    private String player1;

    @Column(name = "PLAYER_2")
    private String player2;

    @Column(name = "PLAYER_3")
    private String player3;

    @Column(name = "PLAYER_4")
    private String player4;

    public History() {}

    public History(String player1, String player2, String player3, String player4) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
    }

    public Long getMatchingId() {
        return matchingId;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public String getPlayer4() {
        return player4;
    }

    @Override
    public String toString() {
        return "History{" +
                "matchingId=" + matchingId +
                ", player1='" + player1 + '\'' +
                ", player2='" + player2 + '\'' +
                ", player3='" + player3 + '\'' +
                ", player4='" + player4 + '\'' +
                '}';
    }
}
