package com.myblog.service.impl;

import com.myblog.entites.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;
import com.myblog.repository.Postrepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private Postrepository postRepo;

    public PostServiceImpl(Postrepository postRepo) {

        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
      Post post =mapToEntity(postDto);
       Post newPost= postRepo.save(post);
     PostDto dto=  mapToDto(newPost);
        return dto;
    }

    @Override
    public PostResponse getAllLeads(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,pageSize, sort);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();

     //   List<Post> posts= postRepo.findAll();
        List<PostDto> contents = content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse=new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
      postResponse.setTotalPages(posts.getTotalPages());
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
    public PostDto updateOnelead(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
   post.setId(postDto.getId());
   post.setTitle(postDto.getTitle());
   post.setDescription(postDto.getDescription());
   post.setContent(postDto.getContent());
        Post save = postRepo.save(post);
        return mapToDto(save);
    }

    @Override
    public void deletelead(long id) {
        Post posts = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        postRepo.delete(posts);
    }

    private PostDto mapToDto(Post post) {
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }
}
