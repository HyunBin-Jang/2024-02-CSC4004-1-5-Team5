package com.travelkit.backend.controller;


import com.fasterxml.jackson.core.JsonProcessingException;

import com.travelkit.backend.domain.LoginRequest;
import com.travelkit.backend.domain.Member;
import com.travelkit.backend.service.MemberService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/members/new")
    public String create(@RequestBody Member member){
        System.out.println(member.getId()+ " " + member.getName() + " " + member.getPassword() + " " + member.getEmail());
        memberService.join(member);
        return "ok";
    }
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest){
        System.out.println(loginRequest.getLoginId() + " " + loginRequest.getPassword());
        Optional<Member> member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());
        return member.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // 값이 존재하면 Member 객체와 200 OK 반환
                .orElseGet(() -> new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND));    // 값이 없으면 메시지와 404 NOT FOUND 반환
    }
    @GetMapping(value = "/members")
    public List<Member> list() {
        return memberService.findMembers();
    }
}
