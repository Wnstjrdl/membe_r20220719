package com.its.example.service;

import com.its.example.dto.CommentDTO;
import com.its.example.entity.BoardEntity;
import com.its.example.entity.CommentEntity;
import com.its.example.entity.MemberEntity;
import com.its.example.repository.BoardRepository;
import com.its.example.repository.CommentRepository;
import com.its.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private  final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentDTO commentDTO) {
        MemberEntity memberEntity=memberRepository.findByMemberEmail(commentDTO.getCommentWriter()).get();
        BoardEntity boardEntity=boardRepository.findById(commentDTO.getBoardId()).get();
        CommentEntity commentEntity=CommentEntity.toSaveEntity(commentDTO,boardEntity,memberEntity);

        return  commentRepository.save(commentEntity).getId();
    }


    public List<CommentDTO> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = boardEntity.getCommentEntityList();
        List<CommentDTO> commentDTOList= new ArrayList<>();

        for (CommentEntity commentEntity : commentEntityList){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(commentEntity);
            commentDTOList.add(commentDTO);
        }
        return  commentDTOList;
    }


    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
