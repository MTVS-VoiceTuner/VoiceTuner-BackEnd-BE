package com.backend.voicetuner.voicmodule.domain.solution.repository;

import com.backend.voicetuner.voicmodule.domain.solution.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<Solution, Long> {
    Solution findByUserIdAndSongId(Long userId, Long songId);
    List<Solution> findAllByUserId(Long userId);
}