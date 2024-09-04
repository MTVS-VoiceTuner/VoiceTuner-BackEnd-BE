package com.backend.voicetuner.login.member.repository;

import com.backend.voicetuner.login.member.domain.Member;

import java.util.Optional;

public interface MemberRepositoryCustom {

    Optional<Member> findByEmail(String email);
}
