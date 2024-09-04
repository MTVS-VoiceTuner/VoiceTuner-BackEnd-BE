package com.backend.voicetuner.communication.model.repository;


import com.backend.voicetuner.communication.model.entity.Script;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, Long> {

}