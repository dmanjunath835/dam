package com.myblogapp77.repository;

import com.myblogapp77.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findById(long postId);

}
