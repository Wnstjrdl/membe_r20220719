package com.its.example.dto;

import com.its.example.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {
    private  Long id;
    private  String boardTitle;
    private  String boardWriter;
    private  String boardContents;
    private  int boardHits;
    private LocalDateTime boardCreatedDate;
    private MultipartFile boardFile;
    private  String boardFileName;

    public BoardDTO(Long id, String boardTitle, String boardWriter, int boardHits, LocalDateTime boardCreatedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.boardWriter = boardWriter;
        this.boardHits = boardHits;
        this.boardCreatedDate = boardCreatedDate;
    }
    public  static BoardDTO ToBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardFileName(boardEntity.getBoardFileName());
        boardDTO.setBoardCreatedDate(boardEntity.getBoardCreatedDate());
        return boardDTO;
    }


}
