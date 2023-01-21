package com.mylife.service.impl;

import com.mylife.entites.Post;
import com.mylife.exception.ResourceNotFoundException;
import com.mylife.payload.PostDto;
import com.mylife.payload.PostResponse;
import com.mylife.repository.PostRepository;
import com.mylife.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
   private  PostRepository postRepo;
   private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepo,ModelMapper mapper ) {
        this.postRepo = postRepo;
        this.mapper=mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
     Post post= mapToEntity(postDto);
        Post posts = postRepo.save(post);
   PostDto dto= mapToDto(posts);

        return dto;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
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
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );

        return mapToDto(post);
    }

    @Override
    public PostDto updateOnePost(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
   //post.setId(postDto.getId());
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setContent(postDto.getContent());
        Post save = postRepo.save(post);
        return mapToDto(save);
    }

    @Override
    public void deleteOne(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        postRepo.delete(post);
    }

    private PostDto mapToDto(Post posts) {
        PostDto postDto = mapper.map(posts, PostDto.class);

//
//        PostDto postDto=new PostDto();
//        postDto.setId(posts.getId());
//   postDto.setTitle(posts.getTitle());
//   postDto.setDescription(posts.getDescription());
//   postDto.setContent(posts.getContent());
   return postDto;
    }

    private Post mapToEntity(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);

//        Post post=new Post();
//        post.setContent(postDto.getContent());
//post.setTitle(postDto.getTitle());
//post.setDescription(postDto.getDescription());
   return post;
    }
}
