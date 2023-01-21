package com.life.controller;

import com.life.payload.PostDto;
import com.life.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    private PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> SavePost(@RequestBody PostDto postDto){
        PostDto postDto1 = postService.saveOne(postDto);
  return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
@GetMapping
    public List<PostDto> getOneLead(@RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
                                    @RequestParam(value="pageSize",defaultValue = "10",required = false)int pageSize){
    return postService.getAllPosts(pageNo, pageSize);
}
}
