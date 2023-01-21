package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.impl.PostService;
import com.myblog.utlies.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class postRestController {
    private PostService postService;

    public postRestController(PostService postService) {
        this.postService = postService;
    }
 @PostMapping
    public ResponseEntity<PostDto> createDto(@RequestBody PostDto postDto){
       return new ResponseEntity<>( postService.createPost(postDto), HttpStatus.CREATED);
    }
    @GetMapping
    public PostResponse getAllLeads(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue =AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue =AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){



     return   postService.getAllLeads(pageNo,pageSize,sortBy,sortDir);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getOneLead(@PathVariable("id") long id){
        return  ResponseEntity.ok(postService.getOneLead(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOneLead(@RequestBody PostDto postDto,@PathVariable("id") long id){
        PostDto dto1 = postService.updateOnelead(postDto, id);
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOnelead(@PathVariable("id") long id){
     postService.deletelead(id);
     return new ResponseEntity<>("Record Was Delete",HttpStatus.OK);
    }

}
