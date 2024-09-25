package com.backend.voicetuner.voicmodule.domain.solution.service;

import com.backend.voicetuner.login._core.jwt.JWTTokenProvider;
import com.backend.voicetuner.login.refreshToken.repository.RefreshTokenRepository;
import com.backend.voicetuner.voicmodule.application.dto.SolutionDTO;
import com.backend.voicetuner.voicmodule.domain.solution.model.Solution;
import com.backend.voicetuner.voicmodule.domain.solution.repository.SolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionService {

    // JWTTokenProvider 주입
    private JWTTokenProvider jwtTokenProvider;

    private SolutionRepository solutionRepository;


    @Autowired
    public SolutionService(SolutionRepository solutionRepository, JWTTokenProvider jwtTokenProvider) {
        this.solutionRepository = solutionRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * token으로 solution 찾기
     * token -> userId -> solutionList
     */
    public List<SolutionDTO> findSolutionByAccessToken(String token) {

        // accessToken에서 userId 추출
        String userId = findUserIdByToken(token);
        System.out.println("userId = " + userId);

        return findSolutionsByUserId(Long.parseLong(userId));  // userId를 Long 타입으로 변환하여 사용
    }

    /**
     * token으로 userId 찾기
     */
    public String findUserIdByToken(String token) {

        // JWTTokenProvider를 사용해 accessToken에서 userId 추출
        return jwtTokenProvider.getUserIdFromAccessToken(token);
    }


    /**
     * create solution
     */
    @Transactional
    public void recordSolution(SolutionDTO solutionDTO) {

        // solutionDTO가 null이면 에러
        if (solutionDTO == null) {
            throw new IllegalArgumentException("solutionDTO is null");
        }

        Solution solutionEntity = new Solution(
                solutionDTO.getUserId(),
                solutionDTO.getSongId(),
                solutionDTO.getTrackId(),
                solutionDTO.getAiAnswer(),
                solutionDTO.getAiShortAnswer(),
                solutionDTO.getTempoScore(),
                solutionDTO.getPitchScore(),
                solutionDTO.getUserVocalRange(),
                solutionDTO.getFinalScore()
        );

        solutionRepository.save(solutionEntity);
    }

    /**
     * update solution
     */
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

    /**
     * delete solution
     */
    @Transactional
    public void deleteSolution(Long userId, Long songId) {
        Solution target = solutionRepository.findByUserIdAndSongId(userId, songId);
        solutionRepository.delete(target);
    }

    public Solution findSolutionByUserIdAndSongId(Long userId, Long songId) {
        return solutionRepository.findByUserIdAndSongId(userId, songId);
    }

    /**
     * find solution by userId
     */
    public List<SolutionDTO> findSolutionsByUserId(Long userId) {

        List<SolutionDTO> result = new ArrayList<>();

        // 응답 확인용
        System.out.println("findSolutionsByUserId_userId = " + userId);

        List<Solution> finds = solutionRepository.findAllByUserId(userId);

        for (Solution solution : finds) {
            result.add(
                    new SolutionDTO(
                            solution.getUserId(),
                            solution.getSongId(),
                            solution.getTrackId(),
                            solution.getAiAnswer(),
                            solution.getAiShortAnswer(),
                            solution.getTempoScore(),
                            solution.getPitchScore(),
                            solution.getUserVocalRange(),
                            solution.getFinalScore()
                    )
            );
        }

        return result;
    }

    /**
     * find solution by songId
     */
    // SongId로 solution 찾기
    public SolutionDTO findSolutionBySongId(Long userId, Long songId) {

        SolutionDTO result = null;

        Solution solution = solutionRepository.findByUserIdAndSongId(userId, songId);

        if (solution != null) {
            result = new SolutionDTO(
                    solution.getUserId(),
                    solution.getSongId(),
                    solution.getTrackId(),
                    solution.getAiAnswer(),
                    solution.getAiShortAnswer(),
                    solution.getTempoScore(),
                    solution.getPitchScore(),
                    solution.getUserVocalRange(),
                    solution.getFinalScore()
            );
        }

        return result;
    }


}
