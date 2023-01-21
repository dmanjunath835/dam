package com.myblog.service.impl;

import com.myblog.entites.Post;
import com.myblog.exception.ResourceNotFoundException;
import com.myblog.payload.PostDto;
import com.myblog.repository.PostRepository;
import com.myblog.service.PostService;
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
    public PostDto createPost(PostDto postDto) {
       Post post= mapToEntity(postDto);
        Post posts = postRepo.save(post);
        PostDto dto = mapToDto(posts);
        return dto;
    }
    @Override
    public List<PostDto> getAll(int pageNo,int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        Page<Post> posts = postRepo.findAll(pageable);
        List<Post> content = posts.getContent();
        //  List<Post> posts = postRepo.findAll();
        return content.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
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
        Post posts = postRepo.save(post);
        return mapToDto(posts);
    }

    @Override
    public void deleteOne(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
         postRepo.delete(post);
    }

    private PostDto mapToDto(Post post) {
    PostDto postDto=new PostDto();
    postDto.setId(post.getId());
    postDto.setName(post.getName());
    postDto.setAge(post.getAge());
        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
   Post post=new Post();
   post.setName(postDto.getName());
   post.setAge(postDto.getAge());
return post;
    }

}
