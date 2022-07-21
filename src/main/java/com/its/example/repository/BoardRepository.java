package com.its.example.repository;

import com.its.example.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardRepository extends JpaRepository<BoardEntity,Long> {


    List<BoardEntity> findByBoardTitleContaining(String q1);

}