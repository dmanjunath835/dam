package com.mylife.service.impl;

import com.mylife.entites.Comment;
import com.mylife.entites.Post;
import com.mylife.exception.ResourceNotFoundException;
import com.mylife.payload.CommentDto;
import com.mylife.repository.CommentRepository;
import com.mylife.repository.PostRepository;
import com.mylife.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper=mapper;
    }

    @Override
    public CommentDto createComment(long postId,CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = mapToComment(commentDto);
        comment.setPost(post);
        Comment dto = commentRepository.save(comment);
                  return   mapToDto(dto);
    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
    return comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto getComment( long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        return mapToDto(comment);
    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());
        Comment c = commentRepository.save(comment);
  return mapToDto(c);

    }

    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post","id",postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment", "id", commentId)
        );
        commentRepository.delete(comment);
    }

    Comment mapToComment(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);


//        Comment comment=new Comment();
//        comment.setBody(commentDto.getBody());
//        comment.setName(commentDto.getName());
//         comment.setEmail(commentDto.getEmail());
        return comment;
   }

   CommentDto mapToDto(Comment comment){
   CommentDto commentDto = mapper.map(comment, CommentDto.class);
//       CommentDto commentDto=new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
        return commentDto;
   }
}
