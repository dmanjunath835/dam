package com.life.service.impl;

import com.life.entites.Post;
import com.life.payload.PostDto;
import com.life.repository.PostRepository;
import com.life.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PostDto saveOne(PostDto postDto) {
        Post post = mapToentity(postDto);
        Post posts = postRepo.save(post);
        PostDto dto = mapToDto(posts);
        return dto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        return content.stream().map(post-> mapToDto(post)).collect(Collectors.toList());
    }
    private PostDto mapToDto(Post posts) {
            PostDto postDto=new PostDto();
            postDto.setId(posts.getId());
            postDto.setName(posts.getName());
            postDto.setAge(posts.getAge());
   return postDto;
    }


    private Post mapToentity(PostDto postDto) {
        Post post=new Post();
        post.setName(postDto.getName());
        post.setAge(postDto.getAge());
        return post;
    }
}