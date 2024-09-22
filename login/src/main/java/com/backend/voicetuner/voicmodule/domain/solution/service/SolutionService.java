package com.backend.voicetuner.voicmodule.domain.solution.service;

import com.backend.voicetuner.voicmodule.application.dto.SolutionDTO;
import com.backend.voicetuner.voicmodule.domain.solution.model.Solution;
import com.backend.voicetuner.voicmodule.domain.solution.repository.SolutionRepository;
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
    public Solution recordSolution(Long userId, Long songId, SolutionDTO solutionDTO) {

        Solution solutionEntity = new Solution(userId, songId,
                solutionDTO.getBeatScore(),
                solutionDTO.getPitchScore(),
                solutionDTO.getVocalRange(),
                solutionDTO.getTotalScore());

        return solutionRepository.save(solutionEntity);
    }

    @Transactional
    public SolutionDTO updateSolution(Long userId, Long songId, SolutionDTO solutionDTO) {
        if (solutionDTO == null) {
            return null;
        }

        Solution target = solutionRepository.findByUserIdAndSongId(userId, songId);

        if (target == null) {
            target = new Solution();
            target.setUserId(userId);
            target.setSongId(songId);
        }
        target.setBeatScore(solutionDTO.getBeatScore());
        target.setPitchScore(solutionDTO.getPitchScore());
        target.setVocalRange(solutionDTO.getVocalRange());
        target.setTotalScore(solutionDTO.getTotalScore());
        solutionRepository.save(target);

        return solutionDTO;

    }

    @Transactional
    public void deleteSolution(Solution solution) {
        Solution target = solutionRepository.findByUserIdAndSongId(solution.getUserId(), solution.getSongId());
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
                            solution.getBeatScore(),
                            solution.getPitchScore(),
                            solution.getVocalRange(),
                            solution.getTotalScore()
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
                    solution.getBeatScore(),
                    solution.getPitchScore(),
                    solution.getVocalRange(),
                    solution.getTotalScore()
            );
        }

        return result;
    }

}
