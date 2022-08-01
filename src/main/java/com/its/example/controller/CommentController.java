package com.its.example.controller;

import com.its.example.dto.CommentDTO;
import com.its.example.service.CommentService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO){
        commentService.save(commentDTO);
        List<CommentDTO> commentDTOList=commentService.findAll(commentDTO.getBoardId());

        return commentDTOList;
    }
    @GetMapping("/{boardId}")
    public  @ResponseBody List<CommentDTO> findAll(@PathVariable("boardId") Long boardId, Model model ,@ModelAttribute CommentDTO commentDTO){
        List<CommentDTO> commentDTOList=commentService.findAll(commentDTO.getBoardId());
        model.addAttribute("commentList",commentDTOList);
        return commentDTOList;
    }

        @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Long id){
        commentService.deleteById(id);

        return new ResponseEntity(HttpStatus.OK);
        }





}
