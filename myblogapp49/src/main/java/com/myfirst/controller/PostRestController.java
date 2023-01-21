package com.myfirst.controller;

import com.myfirst.payload.PostDto;
import com.myfirst.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    private PostService postSer;

    public PostRestController(PostService postSer) {
        this.postSer = postSer;
    }


    @PostMapping
    public ResponseEntity<PostDto> cerateDto(@RequestBody PostDto postDto){
      return new ResponseEntity<>( postSer.createPost(postDto), HttpStatus.CREATED);
    }

@GetMapping
    public List<PostDto> getAllLead(){
       return postSer.getAll();
}
@GetMapping("/{id}")

    public ResponseEntity<PostDto> getOneLead(@PathVariable("id") long id){
      return ResponseEntity.ok( postSer.getOneLead(id)) ;
}

@PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOne(@RequestBody PostDto postDto,@PathVariable("id") long id){
    PostDto posts = postSer.updateOne(postDto, id);
    return new ResponseEntity<>(posts,HttpStatus.OK);
}

@DeleteMapping("/{id}")
  public ResponseEntity<String> deleteOne(@PathVariable("id") long id){
     postSer.delete(id);
    return new ResponseEntity<>("Record is successfully delete",HttpStatus.OK);
}

@PutMapping("/update/{id}")
    public ResponseEntity<String> updateOneLead(@PathVariable("id") long id,@RequestBody PostDto postDto){
     postSer.updateOnelead(id);
    return new ResponseEntity<>("Record is updated",HttpStatus.OK);
}

}
