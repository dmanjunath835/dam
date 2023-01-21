package com.myfirst.service.impl;

import com.myfirst.entites.Post;
import com.myfirst.exception.ResourceNotFoundException;
import com.myfirst.payload.PostDto;
import com.myfirst.repository.PostRepository;
import com.myfirst.service.PostService;
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
     Post post=mapToEntity(postDto);
     Post posts = postRepo.save(post);
     PostDto dto =mapToDto(posts);
        return dto;
    }

    @Override
    public List<PostDto> getAll() {
        List<Post> posts = postRepo.findAll();
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getOneLead(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        return mapToDto(post);
    }

    @Override
    public PostDto updateOne(PostDto postDto, long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        post.setId(postDto.getId());
        post.setName(postDto.getName());
        post.setAge(postDto.getAge());
        Post save = postRepo.save(post);

        return mapToDto(save);
    }

    @Override
    public void delete(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
    postRepo.delete(post);

    }

    @Override
    public PostDto updateOnelead(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
        post.setId(post.getId());
        post.setName(post.getName());
        post.setAge(post.getAge());
        Post save = postRepo.save(post);
     System.out.println(save);
      return mapToDto(save);
    }


    private Post mapToEntity(PostDto postDto) {
        Post post =new Post();
        post.setName(postDto.getName());
        post.setAge(postDto.getAge());
        return post;
    }

    private PostDto mapToDto(Post post) {
PostDto postDto=new PostDto();
postDto.setId(post.getId());
postDto.setName(post.getName());
postDto.setAge(post.getAge());
return postDto;
    }
}
