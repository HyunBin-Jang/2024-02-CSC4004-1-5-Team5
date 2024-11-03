package com.travelkit.backend.service;

import com.travelkit.backend.Repository.MemberRepository;
import com.travelkit.backend.domain.Member;
import jakarta.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Test
    public void join() throws Exception {
        //Given
        Member member = new Member();
        member.setName("kim");
        //When
        String saveId = memberService.join(member);
        //Then
        assertEquals(member, memberRepository.findById(saveId));
    }
    @Test(expected = IllegalStateException.class)
    public void duplicated_except() throws Exception {
        //Given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //When
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.
        //Then
        fail("예외가 발생해야 한다.");
    }
}
