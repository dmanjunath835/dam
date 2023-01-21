package com.myblogapp77.controller;

import com.myblogapp77.payload.PostDto;
import com.myblogapp77.payload.PostResponse;
import com.myblogapp77.service.PostService;
import com.myblogapp77.utiles.AppConstant;
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
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
     return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED)   ;
    }

    @GetMapping
    public PostResponse getAll(@RequestParam(value="pageNo",defaultValue = AppConstant.DEFAULT_PAGE_NO,required = false)int pageNo,
                               @RequestParam(value="pageSize",defaultValue = AppConstant.DEFAULT_PAGE_SIZE,required = false)int pageSize,
                               @RequestParam(value="sortBy",defaultValue =AppConstant.DEFAULT_SORT_BY,required = false)String sortBy,
                               @RequestParam(value="sortDir",defaultValue =AppConstant.DEFAULT_SORT_DIR,required = false)String sortDir     ){
     return   postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);

    }


}
