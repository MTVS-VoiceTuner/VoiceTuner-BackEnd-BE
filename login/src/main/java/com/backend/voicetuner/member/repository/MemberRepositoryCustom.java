package com.backend.voicetuner.member.repository;

import com.backend.voicetuner.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
}
