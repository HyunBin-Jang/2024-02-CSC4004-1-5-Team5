package com.travelkit.backend.controller;

import com.travelkit.backend.domain.LoginRequest;
import com.travelkit.backend.domain.Member;
import com.travelkit.backend.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response){
        System.out.println(loginRequest.getLoginId() + " " + loginRequest.getPassword());
        Optional<Member> member = memberService.login(loginRequest.getLoginId(), loginRequest.getPassword());
        Cookie cookie = new Cookie("userId", loginRequest.getLoginId());
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(12 * 60 * 60);

        response.addCookie(cookie);
        return member.<ResponseEntity<Object>>map(value -> new ResponseEntity<>(value, HttpStatus.OK)) // 값이 존재하면 Member 객체와 200 OK 반환
                .orElseGet(() -> new ResponseEntity<>("Member not found", HttpStatus.NOT_FOUND));    // 값이 없으면 메시지와 404 NOT FOUND 반환
    }
    @GetMapping(value = "/members")
    public List<Member> list() {
        return memberService.findMembers();
    }

    @GetMapping(value = "/members/{id}")
    public Optional<Member> memberInfo(@PathVariable("id") String id){
        return memberService.findOne(id);
    }
}
