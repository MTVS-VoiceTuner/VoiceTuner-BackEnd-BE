package com.backend.voicetuner.voicmodule.domain.matching.service;

import com.backend.voicetuner.voicmodule.application.dto.MatchingDTO;
import com.backend.voicetuner.voicmodule.domain.matching.model.History;
import com.backend.voicetuner.voicmodule.domain.matching.repository.MatchingRepository;
import org.springframework.boot.actuate.endpoint.jackson.EndpointObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingHistoryService {

    private MatchingRepository matchingRepository;

    public MatchingHistoryService(MatchingRepository matchingRepository) {
        this.matchingRepository = matchingRepository;
    }

    public MatchingDTO registMatching(MatchingDTO matchingDTO) {

        if (matchingDTO == null)
            return null;

        History matchingEntity = new History(
                matchingDTO.getPlayer1(),
                matchingDTO.getPlayer2(),
                matchingDTO.getPlayer3(),
                matchingDTO.getPlayer4()
        );
        matchingRepository.save(matchingEntity);

        return matchingDTO;
    }

    public List<MatchingDTO> getAllMatchingHistory() {
        List<MatchingDTO> result = new ArrayList<>();
        List<History> finds = matchingRepository.findAll();

        for (History history : finds) {
            result.add( new MatchingDTO(
                    history.getPlayer1(),
                    history.getPlayer2(),
                    history.getPlayer3(),
                    history.getPlayer4())
            );
        }
        return result;
    }

    public List<MatchingDTO> findUserMatchingHistory(Long userId) {
        List<MatchingDTO> result = new ArrayList<>();
        List<History> finds = matchingRepository.findAllByPlayer1OrPlayer2OrPlayer3OrPlayer4(
                userId,
                userId,
                userId,
                userId
            );

        for (History history : finds) {
            result.add( new MatchingDTO(
                    history.getPlayer1(),
                    history.getPlayer2(),
                    history.getPlayer3(),
                    history.getPlayer4())
            );
        }
        return result;
    }
}
