package com.backend.voicetuner.voicmodule.domain.matching.repository;

import com.backend.voicetuner.voicmodule.domain.matching.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchingRepository extends JpaRepository<History, Long> {
    List<History> findAllByPlayer1OrPlayer2OrPlayer3OrPlayer4(
            String player1,
            String player2,
            String player3,
            String player4);
}
