package com.its.example.repository;

import com.its.example.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
