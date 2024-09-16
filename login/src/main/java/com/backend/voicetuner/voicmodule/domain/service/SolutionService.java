package com.backend.voicetuner.voicmodule.domain.service;

import com.backend.voicetuner.voicmodule.domain.model.Solution;
import com.backend.voicetuner.voicmodule.domain.repository.SolutionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SolutionService {

    private SolutionRepository solutionRepository;

    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Transactional
    public Solution recordSolution(Solution solution) {

        if (solution != null) {
            return solutionRepository.save(solution);
        }
        return null;
    }

    @Transactional
    public Solution updateSolution(Solution solution) {
        if (solution == null) {
            return null;
        }

        Solution target = solutionRepository.findByUserIdAndSongId(solution.getUserId(), solution.getSongId());

        if (target != null) {
            target.setBeatScore(solution.getBeatScore());
            target.setPitchScore(solution.getPitchScore());
            target.setVocalRange(solution.getVocalRange());
            target.setTotalScore(solution.getTotalScore());
        }
        return solutionRepository.save(solution);

    }

    @Transactional
    public void deleteSolution(Solution solution) {
        Solution target = solutionRepository.findByUserIdAndSongId(solution.getUserId(), solution.getSongId());
        solutionRepository.delete(target);
    }

    public Solution findSolutionByUserIdAndSongId(Long userId, Long songId) {
        return solutionRepository.findByUserIdAndSongId(userId, songId);
    }

    public List<Solution> findSolutionsByUserId(Long userId) {
        return solutionRepository.findAllByUserId(userId);
    }

}
