package com.backend.voicetuner.communication.model.repository;

import com.backend.voicetuner.communication.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}