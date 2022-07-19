package com.its.example.controller;

import com.its.example.common.PagingConst;
import com.its.example.dto.BoardDTO;
import com.its.example.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    // 페이징
    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        Page<BoardDTO> boardList = boardService.paging(pageable);
        model.addAttribute("boardList", boardList);
        int startPage= (((int) (Math.ceil((double)pageable.getPageNumber()/ PagingConst.BLOCK_LIMIT))) -1) *PagingConst.BLOCK_LIMIT+1;
        int endPage = ((startPage + PagingConst.BLOCK_LIMIT - 1) < boardList.getTotalPages()) ? startPage + PagingConst.BLOCK_LIMIT - 1 : boardList.getTotalPages();
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
        return  "boardPages/paging";
    }
    //상세조회
    @GetMapping("/detail/{id}")
    public  String findById(@PathVariable Long id,Model model) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);

        return "boardPages/detail";
    }
}
