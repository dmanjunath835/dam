package com.myblogapp77.service.impl;

import com.myblogapp77.entites.Comment;
import com.myblogapp77.entites.Post;
import com.myblogapp77.exception.ResourceNotFoundException;
import com.myblogapp77.payload.CommentDto;
import com.myblogapp77.repository.CommentRepository;
import com.myblogapp77.repository.PostRepository;
import com.myblogapp77.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
  private CommentRepository commentRepository;
  private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
           comment.setPost(post);
        Comment dto= commentRepository.save(comment);
        return mapToDto(dto);

    }

    @Override
    public List<CommentDto> getCommentPostId(long postId ) {
        List<Comment> comments = commentRepository.findById(postId);


        return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
    }



    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto=new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setBody(comment.getName());
        commentDto.setEmail(comment.getEmail());

        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
    Comment comment=new Comment();
    comment.setName(commentDto.getName());
   // comment.setBody(commentDto.getBody());
    comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

    return comment;
    }
}
