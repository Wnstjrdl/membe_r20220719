package com.its.example.controller;

import com.its.example.dto.BoardDTO;
import com.its.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private  final BoardService boardService;

    //글쓰기 화면요청
   @GetMapping("/save")
    public String saveForm(){
        return  "boardPages/save";
    }
    //글쓰기 처리
    @PostMapping("/save")
    public  String save(@ModelAttribute BoardDTO boardDTO)throws IOException {
       boardService.save(boardDTO);
       return "redirect:/board/paging";
    }
}
