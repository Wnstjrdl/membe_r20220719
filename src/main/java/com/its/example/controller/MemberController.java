package com.its.example.controller;

import com.its.example.dto.MemberDTO;
import com.its.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    //회원가입
    @GetMapping("/save")
    public String save(){
        return  "memberPages/save";
    }

    //회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO)throws IOException {
        memberService.save(memberDTO);
        return  "memberPages/login";
    }


    }
