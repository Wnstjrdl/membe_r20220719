package com.its.example.repository;

import com.its.example.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
