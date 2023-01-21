package com.mylife.repository;


import com.mylife.entites.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


   List<Comment> findByPostId(long postId);
   List<Comment>findByName(String name);


}
