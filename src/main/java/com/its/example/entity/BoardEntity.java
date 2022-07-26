package com.its.example.entity;



import com.its.example.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;
    @Column(length = 20)
    private  String boardTitle;
    @Column(length =30 ,nullable = false)
    private  String boardWriter;
    @Column(length =100,nullable = false )
    private  String boardContents;
    @Column
    private int boardHits;
    @Column(length = 500)
    private String boardFileName;

@ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "member_id")
private MemberEntity memberEntity;

@OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true,fetch = FetchType.LAZY )
private List<CommentEntity> commentEntityList=new ArrayList<>();




    public  static  BoardEntity toSaveEntity(BoardDTO boardDTO,MemberEntity memberEntity){
        BoardEntity boardEntity= new BoardEntity();
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(memberEntity.getMemberEmail());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);
        boardEntity.setBoardFileName(boardDTO.getBoardFileName());
        boardEntity.setMemberEntity(memberEntity);
        return boardEntity;
    }

    public  static  BoardEntity toUpdateEntity(BoardDTO boardDTO){
        BoardEntity boardEntity= new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardDTO.getBoardHits());
        boardEntity.setBoardFileName(boardDTO.getBoardFileName());

        return boardEntity;
    }
}
