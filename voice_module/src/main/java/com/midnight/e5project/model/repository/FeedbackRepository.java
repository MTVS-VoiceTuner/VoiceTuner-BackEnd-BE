package com.midnight.e5project.model.repository;

import com.midnight.e5project.model.entity.Feedback;
import com.midnight.e5project.model.entity.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}