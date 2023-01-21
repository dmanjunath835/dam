package com.myblogapp77.controller;

import com.myblogapp77.payload.CommentDto;
import com.myblogapp77.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
private CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") long postId){

    return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }



    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAll(@PathVariable("postId") long postId){
        List<CommentDto> dto = commentService.getCommentPostId(postId);
  return dto;

    }

}
