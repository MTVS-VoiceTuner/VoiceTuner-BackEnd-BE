package com.backend.voicetuner.voicmodule.application.controller;

import com.backend.voicetuner.voicmodule.application.dto.MatchingDTO;
import com.backend.voicetuner.voicmodule.domain.matching.service.MatchingHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matching")
public class MatchingController {

    private MatchingHistoryService matchingService;

    public MatchingController(MatchingHistoryService matchingService) {
        this.matchingService = matchingService;
    }

    @PostMapping("/new-match")
    public ResponseEntity<MatchingDTO> newMatch(@RequestBody MatchingDTO matchingDTO) {
        MatchingDTO result = matchingService.registMatching(matchingDTO);

        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/find-match")
    public ResponseEntity<?> findMatch(@RequestParam Long userId) {
        List<MatchingDTO> result = matchingService.findUserMatchingHistory(userId);
        if (result.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(result);
    }
}
