package com.myblog.service.impl;

import com.myblog.entites.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post dto = postRepository.save(post);
        return mapToDto(dto);

    }

    @Override
    public PostResponse getAll(int pageNo, int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();//if case it will used
        Pageable pageable= PageRequest.of(pageNo, pageSize,sort);
        Page<Post> posts = postRepository.findAll(pageable);
        List<Post> content = posts.getContent();
        List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getNumberOfElements());
        postResponse.setLast(posts.isLast());

   return postResponse;
    }

    @Override
    public PostDto getOneLead(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );


        return mapToDto(post);
    }

    @Override
    public PostDto updateOne(long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
           post.setId(postDto.getId());
           post.setTitle(postDto.getTitle());
           post.setContent(postDto.getContent());
           post.setDescription(postDto.getDescription());

        Post save = postRepository.save(post);

        return mapToDto(save);
    }

    Post mapToEntity(PostDto postDto){
        Post post=new Post();
        post.setTitle(postDto.getTitle());
       post.setDescription(postDto.getDescription());
  post.setContent(postDto.getContent());
  return post;
    }

   PostDto mapToDto(Post post){
      PostDto postDto=new PostDto();
      postDto.setId(post.getId());
      postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getContent());
        postDto.setContent(post.getContent());
        return postDto;
   }

}
