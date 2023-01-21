package com.myblog.service.impl;

import com.myblog.entites.Comment;
import com.myblog.entites.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.CommentDto;
import com.myblog.repository.CommentRepository;
import com.myblog.repository.Postrepository;
import com.myblog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private Postrepository postrepository;

    public CommentServiceImpl(CommentRepository commentRepository, Postrepository postrepository) {
        this.commentRepository = commentRepository;
        this.postrepository = postrepository;
    }

    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Post post = postrepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comments = mapToComment(commentDto);
        comments.setPost(post);
        Comment comment = commentRepository.save(comments);
          return  mapToDto(comment);

    }

     Comment mapToComment(CommentDto commentDto) {
        Comment comment=new Comment();
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        return comment;
    }


    CommentDto mapToDto(Comment comment){
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getBody());
        commentDto.setEmail(comment.getEmail());
        return commentDto;
    }

}
