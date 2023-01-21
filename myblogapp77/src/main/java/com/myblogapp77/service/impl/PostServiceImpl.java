package com.myblogapp77.service.impl;

import com.myblogapp77.entites.Post;
import com.myblogapp77.payload.PostDto;
import com.myblogapp77.payload.PostResponse;
import com.myblogapp77.repository.PostRepository;
import com.myblogapp77.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post posts = postRepo.save(post);
        return mapToDto(posts);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int PageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable= PageRequest.of(pageNo,PageSize,sort);
        Page<Post> pages = postRepo.findAll(pageable);
        List<Post> contents = pages.getContent();

        List<PostDto> content = contents.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(pages.getNumber());
        postResponse.setPageSize(pages.getSize());
        postResponse.setTotalPages(pages.getTotalPages());
        postResponse.setTotalElements(pages.getTotalElements());
        postResponse.setLast(pages.isLast());


        return postResponse;
    }

    private PostDto mapToDto(Post posts) {
        PostDto postDto=new PostDto();
        postDto.setId(posts.getId());
        postDto.setTitle(posts.getTitle());
        postDto.setDescription(posts.getDescription());
        postDto.setContent(posts.getContent());

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
