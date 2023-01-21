package com.mylife.controller;

import com.mylife.payload.CommentDto;
import com.mylife.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentRestController {

    private CommentService commentService;

    public CommentRestController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable(value="postId") long postId){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


@GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getAllCommentsGetId(@PathVariable("postId")long postId){
    List<CommentDto> dto = commentService.getCommentByPostId(postId);
    return dto;
}
@GetMapping("/posts/{postId}/comments/{id}")
public ResponseEntity<CommentDto> getOneComment(@PathVariable("id") long id){
    CommentDto dto = commentService.getComment(id);
    return new ResponseEntity<>(dto,HttpStatus.OK);

}

@PutMapping("/posts/{postId}/comments/{id}")

    public ResponseEntity<CommentDto> updateOneComment(@PathVariable(value="postId") long postId,@PathVariable(value="id") long commentId,@RequestBody CommentDto commentDto){
    CommentDto dto = commentService.updateComment(postId, commentId, commentDto);
    return new ResponseEntity<>(dto,HttpStatus.OK);

}


@DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteOneComment(@PathVariable("postId") long postId,@PathVariable("id") long commentId){
        commentService.deleteComment(postId,commentId);

return new ResponseEntity<>("Comment Successfully Delete",HttpStatus.OK);
}

}
