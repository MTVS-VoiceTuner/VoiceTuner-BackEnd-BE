package com.backend.voicetuner.voicmodule.application.controller;

import com.backend.voicetuner.voicmodule.domain.solution.model.Solution;
import com.backend.voicetuner.voicmodule.domain.solution.service.SolutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.backend.voicetuner.voicmodule.application.dto.SolutionDTO;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/solution")
public class SolutionController {

    private SolutionService solutionService;

    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("find-all")
    public ResponseEntity<?> findSolutionById(@RequestParam Long userId) {
        List<SolutionDTO> response = solutionService.findSolutionsByUserId(userId);

        if (response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("find-song")
    public ResponseEntity<SolutionDTO> findSolutionBySongId(
            @RequestParam Long userId,
            @RequestParam Long songId) {
        SolutionDTO result = solutionService.findSolutionBySongId(userId, songId);
        if (result == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("record-solution")
    public ResponseEntity<?> recordSolution(
            @RequestParam Long userId,
            @RequestParam Long songId,
            @RequestBody SolutionDTO solutionDTO) {
        SolutionDTO result = solutionService.updateSolution(userId, songId, solutionDTO);

        if (result == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(result);
    }
}
