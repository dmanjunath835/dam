package com.myblog.controller;

import com.myblog.entites.Post;
import com.myblog.payload.PostDto;
import com.myblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
private PostService postSer;

    public PostController(PostService postSer) {
        this.postSer = postSer;
    }

@PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
  //  postSer.createPost(postDto);
    return new ResponseEntity<>( postSer.createPost(postDto), HttpStatus.CREATED);
}
@GetMapping
    public List<PostDto> getAllLeads(
            @RequestParam(value="pageNo",defaultValue = "0",required = false)int pageNo,
            @RequestParam(value="pageSize",defaultValue = "10",required = false)int pageSize

){
       return postSer.getAll(pageNo, pageSize);
}

@GetMapping("/{id}")
    public ResponseEntity<PostDto> getOne(@PathVariable("id") long id){
    PostDto one = postSer.getOneLead(id);
    return new ResponseEntity<>(one,HttpStatus.OK);
}
@PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOne(@RequestBody PostDto postDto,@PathVariable("id") long id){
    PostDto dto = postSer.updateOne(postDto, id);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}
@DeleteMapping("/{id}")
public ResponseEntity<String> delete(@PathVariable("id") long id){
        postSer.deleteOne(id);
        return new ResponseEntity<>("Record successfully Delete!", HttpStatus.OK);
}
}