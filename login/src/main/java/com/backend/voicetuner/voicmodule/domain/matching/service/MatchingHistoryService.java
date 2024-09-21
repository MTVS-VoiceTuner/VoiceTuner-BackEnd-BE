package com.backend.voicetuner.voicmodule.domain.matching.service;

import com.backend.voicetuner.voicmodule.application.dto.HistoryDTO;
import com.backend.voicetuner.voicmodule.domain.matching.model.History;
import com.backend.voicetuner.voicmodule.domain.matching.repository.MatchingRepository;
import org.springframework.boot.actuate.endpoint.jackson.EndpointObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchingHistoryService {

    private final EndpointObjectMapper endpointObjectMapper;
    private MatchingRepository matchingRepository;

    public MatchingHistoryService(MatchingRepository matchingRepository, EndpointObjectMapper endpointObjectMapper) {
        this.matchingRepository = matchingRepository;
        this.endpointObjectMapper = endpointObjectMapper;
    }

    public History registMatching(HistoryDTO historyDTO) {

        if (historyDTO == null)
            return null;

        History matchingEntity = new History(
                historyDTO.getPlayer1(),
                historyDTO.getPlayer2(),
                historyDTO.getPlayer3(),
                historyDTO.getPlayer4());
        return matchingRepository.save(matchingEntity);
    }

    public List<HistoryDTO> getAllMatchingHistory() {
        List<HistoryDTO> result = new ArrayList<>();
        List<History> finds = matchingRepository.findAll();

        for (History history : finds) {
            result.add( new HistoryDTO(
                    history.getPlayer1(),
                    history.getPlayer2(),
                    history.getPlayer3(),
                    history.getPlayer4())
            );
        }
        return result;
    }

    public List<HistoryDTO> findUserMatchingHistory(String email) {
        List<HistoryDTO> result = new ArrayList<>();
        List<History> finds = matchingRepository.findAllByPlayer1OrPlayer2OrPlayer3OrPlayer4(
                email,
                email,
                email,
                email
            );

        for (History history : finds) {
            result.add( new HistoryDTO(
                    history.getPlayer1(),
                    history.getPlayer2(),
                    history.getPlayer3(),
                    history.getPlayer4())
            );
        }
        return result;
    }
}
