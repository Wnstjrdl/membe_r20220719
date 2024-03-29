package com.its.example.repository;

import com.its.example.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    @Modifying
    @Query(value = "update BoardEntity b set  b.boardHits=b.boardHits+1 where b.id=:id")
    void  boardHits(@Param("id") Long id);


  List<BoardEntity> findByBoardTitleContaining(String q1);

}