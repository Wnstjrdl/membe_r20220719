package com.its.example.service;

import com.its.example.common.PagingConst;
import com.its.example.dto.BoardDTO;
import com.its.example.entity.BoardEntity;
import com.its.example.entity.MemberEntity;
import com.its.example.repository.BoardRepository;
import com.its.example.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {


    private  final BoardRepository boardRepository;
    private  final MemberRepository memberRepository;



    public Long save(BoardDTO boardDTO) throws IOException {
        MultipartFile boardFile= boardDTO.getBoardFile();
        String boardFileName=boardFile.getOriginalFilename();
        boardFileName=System.currentTimeMillis()+"_"+boardFileName;
        String savePath= "D:\\springboot_img\\" + boardFileName;

        if (! boardFile.isEmpty()){
            boardFile.transferTo(new File(savePath));

        }
        boardDTO.setBoardFileName(boardFileName);

        Optional<MemberEntity> optionalMemberEntity=
                memberRepository.findByMemberEmail(boardDTO.getBoardWriter());
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            Long savedId = boardRepository.save(BoardEntity.toSaveEntity(boardDTO,memberEntity)).getId();
            return savedId;
        }else {
            return null;
        }

    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page= pageable.getPageNumber();

        page= (page ==1)?0:(page-1);
        Page<BoardEntity> boardEntities=
                boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC,"id")));
        Page<BoardDTO> boardList = boardEntities.map(
                board -> new BoardDTO(board.getId(),
                        board.getBoardTitle(),
                        board.getBoardWriter(),
                        board.getBoardHits(),
                        board.getBoardCreatedDate()


                ));
        return  boardList;
    }

@Transactional
    public BoardDTO findById(Long id) {
        boardRepository.boardHits(id);
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            return BoardDTO.ToBoardDTO(optionalBoardEntity.get());
        }else {
            return  null;
        }
    }

    public void delete(Long id) {boardRepository.deleteById(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.save(BoardEntity.toUpdateEntity(boardDTO));
    }



   public List<BoardDTO> search(String q1) {
       List<BoardEntity> boardEntityList = boardRepository.findByBoardTitleContaining(q1);
       List<BoardDTO>boardDTOList= new ArrayList<>();
      for (BoardEntity boardEntity: boardEntityList) {
          boardDTOList.add(BoardDTO.ToBoardDTO(boardEntity)); }
     return  boardDTOList;}




}
