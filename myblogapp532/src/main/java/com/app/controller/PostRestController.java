package com.app.controller;

import com.app.payload.PostDto;
import com.app.service.PostService;
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
        return new ResponseEntity<>(postService.createPostId(postDto), HttpStatus.CREATED);

    }
@GetMapping
    public List<PostDto> getAll(@RequestParam(value = "pageNO",defaultValue = "0",required = false)int pageNo,
                                @RequestParam(value = "pageNO",defaultValue = "5",required = false)int pageSize,
                                @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
                                @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir                          ){
    List<PostDto> posts = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    return posts;
}

@GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostId(@PathVariable("id") long id){
    PostDto dto = postService.getFindId(id);
    return new ResponseEntity<>(dto,HttpStatus.OK);


}

@PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOne(@RequestBody PostDto postDto,@PathVariable("id") long id){
    PostDto dto = postService.updateOnePost(postDto, id);

    return new ResponseEntity<>(dto,HttpStatus.OK);
}
@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id){
        postService.delete(id);

        return new ResponseEntity<>(" Successfully record Delete",HttpStatus.OK);

}



}
