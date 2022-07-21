package com.its.example.controller;

import com.its.example.dto.MemberDTO;
import com.its.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor

public class MemberController {
    private final MemberService memberService;

    //회원가입
    @GetMapping("/save")
    public String saveForm(){
        return  "memberPages/save";
    }

    //회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO)throws IOException {
        memberService.save(memberDTO);
        return  "memberPages/login";
    }
    //로그인
    @GetMapping("/login")
    public  String loginForm(){
        return  "memberPages/login";
    }
    //로그인 처리
    @PostMapping("/login")
    public  String login(@ModelAttribute MemberDTO memberDTO, Model model, HttpSession session){
        MemberDTO loginResult=memberService.login(memberDTO);
        if(loginResult != null){
            model.addAttribute("loginResult",loginResult);
            session.setAttribute("loginEmail",loginResult.getMemberEmail());
            session.setAttribute("id",loginResult.getId());
            return  "redirect:/board/paging";
        }else {
            return  "memberPages/login";
        }

    }
    // 이메일 중복체크
    @PostMapping("/emailCheck")
    public @ResponseBody String emailCheck(@RequestParam String memberEmail){
        String checkResult= memberService.EmailCheck(memberEmail);
        return  checkResult;
    }
    //로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    //관리자 사이트로 이동
    @GetMapping("/admin")
    public String admin(){
        return "memberPages/admin";
    }
    // 회원 정보 이동
    @GetMapping("/findAll")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList =memberService.findAll();
        model.addAttribute("memberList",memberDTOList);
        return  "memberPages/findAll";
    }
    //회원 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        memberService.delete(id);
        return  "redirect:/member/findAll";
    }
    //마이페이지
    @GetMapping("/myPage")
    public  String myPage(){
        return "/memberPages/myPage";
    }

    //상세조회
    @GetMapping("/detail/{id}")
    public  String findById(@PathVariable Long id,Model model){
        MemberDTO memberDTO=memberService.findById(id);
        model.addAttribute("member",memberDTO);

        return "memberPages/detail";

    }

    //업데이트
    @GetMapping("/update")
    public  String updateForm(HttpSession session, Model model){
        Long id= (Long)session.getAttribute("id");
        MemberDTO memberDTO =memberService.findById(id);
        model.addAttribute("updateMember",memberDTO);
        return "memberPages/update";
    }
    @PostMapping("/update")
    public  String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return  "redirect:/member/detail/"+memberDTO.getId();
    }

    }
