package com.mylife.controller;

import com.mylife.payload.PostDto;
import com.mylife.payload.PostResponse;
import com.mylife.service.PostService;
import com.mylife.utiles.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {
    private PostService postService;

    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createpost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult){

       if(bindingResult.hasErrors()){

           return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.CREATED);


       }
     //  postService.createPost(postDto);
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAll(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false)int pageNo,
                               @RequestParam(value="pageSize",defaultValue =  AppConstants.DEFAULT_PAGE_SIZE,required = false)int pageSize,
                               @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false)String sortBy,
                               @RequestParam(value="sortDir",defaultValue =  AppConstants.DEFAULT_SORT_DIR,required = false)String sortDir){
       return postService.getAllPost(pageNo, pageSize,sortBy,sortDir);
    }
@GetMapping("/{id}")
   public ResponseEntity<PostDto> getOneLead(@PathVariable("id") long id){
    PostDto oneLead = postService.getOneLead(id);
    return new ResponseEntity<>(oneLead,HttpStatus.OK);
}
@PutMapping("/{id}")
    public ResponseEntity<PostDto> updateOne(@RequestBody PostDto postDto,@PathVariable("id") long id){
    PostDto postDto1 = postService.updateOnePost(postDto, id);
    return new ResponseEntity<>(postDto1,HttpStatus.OK);
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOneLead(@PathVariable("id")long id){
        postService.deleteOne(id);
            return new ResponseEntity<>("Delete is Successfully!",HttpStatus.OK);
}

}
