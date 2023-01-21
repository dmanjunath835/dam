package com.myblog.controller;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.service.PostService;
import com.myblog.utlies.AppConstants;
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
    public PostResponse getAll(
            @RequestParam(value="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NO,required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
            @RequestParam(value="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir
    ){

        return postService.getAll(pageNo,pageSize,sortBy,sortDir);

    }

@GetMapping("/{id}")
    public ResponseEntity<PostDto> getOneLead(@PathVariable(value="id") long id){
    PostDto dto = postService.getOneLead(id);


    return new ResponseEntity<>(dto,HttpStatus.OK);
}
@PutMapping("/{id}")
    public ResponseEntity<PostDto> update(@RequestBody PostDto postDto,@PathVariable("id") long id) {
   // PostDto postDto1 = postService.updateOne(id, postDto);
return new ResponseEntity<>(postService.updateOne(id, postDto),HttpStatus.OK);
}

}
