package com.its.example.dto;

import com.its.example.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private  Long id;
    private  Long boardId;
    private  String commentWriter;
    private  String commentContents;
    private LocalDateTime commentCreatedDate;


    public static  CommentDTO toCommentDTO(CommentEntity commentEntity){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setBoardId(commentEntity.getBoardEntity().getId());
        commentDTO.setCommentWriter(commentEntity.getMemberEntity().getMemberEmail());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedDate(commentEntity.getCommentCreatedDate());
        return  commentDTO;
    }
}
