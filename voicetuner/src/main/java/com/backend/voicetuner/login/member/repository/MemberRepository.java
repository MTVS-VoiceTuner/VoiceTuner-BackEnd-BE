package com.backend.voicetuner.login.member.repository;

import com.backend.voicetuner.login.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
    Member findMemberByEmail(String email);
}