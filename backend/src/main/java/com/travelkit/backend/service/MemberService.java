package com.travelkit.backend.service;

import com.travelkit.backend.Repository.MemberRepository;
import com.travelkit.backend.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional //변경
    public String join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers =
                memberRepository.findByName(member.getId());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(String memberId) {
        return memberRepository.findById(memberId);
    }

    public Optional<Member> login(String id, String password) {
        Optional<Member> member = memberRepository.findById(id);
        // 사용자가 존재하고, 비밀번호가 일치하는지 확인
        if (member.isPresent() && Objects.equals(password, member.get().getPassword())) {
            return member; // 로그인 성공
        } else {
            return Optional.empty(); // 로그인 실패
        }
    }
}