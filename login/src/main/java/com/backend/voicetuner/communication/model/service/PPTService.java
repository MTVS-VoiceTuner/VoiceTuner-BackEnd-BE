package com.backend.voicetuner.communication.model.service;

import com.backend.voicetuner.communication.model.entity.Feedback;
import com.backend.voicetuner.communication.model.entity.Script;
import com.backend.voicetuner.communication.model.repository.FeedbackRepository;
import com.backend.voicetuner.communication.model.repository.ScriptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PPTService {

    private ScriptRepository scriptRepository;
    private FeedbackRepository feedbackRepository;

    @Autowired
    public PPTService(ScriptRepository scriptRepository, FeedbackRepository feedbackRepository) {
        this.scriptRepository = scriptRepository;
        this.feedbackRepository = feedbackRepository;
    }

    // 원본 스크립트 저장
    public void registOriginScript(String scriptContent) {
        Script script = new Script();
        script.setContent(scriptContent);
        script.setOrigin(true); // 원본 스크립트 여부 설정

        scriptRepository.save(script); // 스크립트 저장
        System.out.println("Original script saved.");
    }

    // 수정된 스크립트 또는 피드백 저장
    public void registProcessScript(String result) {
        Script script = new Script();
        script.setContent(result);
        script.setOriginal(false); // 수정된 스크립트 설정

        Feedback feedback = new Feedback();
        feedback.setContent("AI Feedback: " + result); // 피드백 생성
        feedback.setScript(script); // 피드백과 스크립트 연결

        scriptRepository.save(script); // 스크립트 저장
        feedbackRepository.save(feedback); // 피드백 저장
        System.out.println("Processed script and feedback saved.");
    }
}
