package com.backend.voicetuner.voicmodule.domain.solution.service;

import com.backend.voicetuner.voicmodule.application.dto.SolutionDTO;
import com.backend.voicetuner.voicmodule.domain.solution.model.Solution;
import com.backend.voicetuner.voicmodule.domain.solution.repository.SolutionRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionService {

    private SolutionRepository solutionRepository;

    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    @Transactional
    public Solution recordSolution(SolutionDTO solutionDTO) {

        if (solutionDTO == null) {
            return null;
        }

        Solution solutionEntity = new Solution(
                solutionDTO.getUserId(),
                solutionDTO.getSongId(),
                solutionDTO.getTrackId(),
                solutionDTO.getAiAnswer(),
                solutionDTO.getTempoScore(),
                solutionDTO.getPitchScore(),
                solutionDTO.getUserVocalRange(),
                solutionDTO.getFinalScore()
        );

        return solutionRepository.save(solutionEntity);
    }

    @Transactional
    public SolutionDTO updateSolution(SolutionDTO solutionDTO) {
        if (solutionDTO == null) {
            return null;
        }

        Solution target = solutionRepository.findByUserIdAndSongId(solutionDTO.getUserId(), solutionDTO.getSongId());

        if (target == null) {
            target = new Solution();
            target.setUserId(solutionDTO.getUserId());
            target.setSongId(solutionDTO.getSongId());
            target.setTrackId(solutionDTO.getTrackId());
        }
        target.setAiAnswer(solutionDTO.getAiAnswer());
        target.setTempoScore(solutionDTO.getTempoScore());
        target.setPitchScore(solutionDTO.getPitchScore());
        target.setUserVocalRange(solutionDTO.getUserVocalRange());
        target.setFinalScore(solutionDTO.getFinalScore());
        solutionRepository.save(target);

        return solutionDTO;

    }

    @Transactional
    public void deleteSolution(Long userId, Long songId) {
        Solution target = solutionRepository.findByUserIdAndSongId(userId, songId);
        solutionRepository.delete(target);
    }

    public Solution findSolutionByUserIdAndSongId(Long userId, Long songId) {
        return solutionRepository.findByUserIdAndSongId(userId, songId);
    }

    public List<SolutionDTO> findSolutionsByUserId(Long userId) {
        List<SolutionDTO> result = new ArrayList<>();
        List<Solution> finds = solutionRepository.findAllByUserId(userId);

        for (Solution solution : finds) {
            result.add(
                    new SolutionDTO(
                            solution.getUserId(),
                            solution.getSongId(),
                            solution.getTrackId(),
                            solution.getAiAnswer(),
                            solution.getTempoScore(),
                            solution.getPitchScore(),
                            solution.getUserVocalRange(),
                            solution.getFinalScore()
                    )
            );
        }
        return result;
    }

    public SolutionDTO findSolutionBySongId(Long userId, Long songId) {

        SolutionDTO result = null;

        Solution solution = solutionRepository.findByUserIdAndSongId(userId, songId);

        if (solution != null) {
            result = new SolutionDTO(
                    solution.getUserId(),
                    solution.getSongId(),
                    solution.getTrackId(),
                    solution.getAiAnswer(),
                    solution.getTempoScore(),
                    solution.getPitchScore(),
                    solution.getUserVocalRange(),
                    solution.getFinalScore()
            );
        }

        return result;
    }

}
