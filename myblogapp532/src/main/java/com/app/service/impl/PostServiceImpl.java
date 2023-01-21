package com.app.service.impl;

import com.app.entites.Post;
import com.app.exception.ResourceNotFoundException;
import com.app.payload.PostDto;
import com.app.repository.PostRepository;
import com.app.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.config.SortedResourcesFactoryBean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper mapper) {
        this.postRepository = postRepository;
        this.mapper=mapper;
    }

    @Override
    public PostDto createPostId(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post posts = postRepository.save(post);
       return  mapToDto(posts);

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);

        Page<Post> page = postRepository.findAll(pageable);
        List<Post> content = page.getContent();

        return content.stream().map(p->mapToDto(p)).collect(Collectors.toList());
    }

    @Override
    public PostDto getFindId( long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );


        return mapToDto(post);
    }

    @Override
    public PostDto updateOnePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
         post.setTitle(postDto.getTitle());
         post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post posts = postRepository.save(post);


        return mapToDto(posts);
    }

    @Override
    public void delete(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", id)
        );
    postRepository.delete(post);

    }

    private PostDto mapToDto(Post posts) {
        PostDto postDto = mapper.map(posts, PostDto.class);

        return postDto;
    }

    private Post mapToEntity(PostDto postDto) {
        Post post = mapper.map(postDto, Post.class);


        return post;
    }
}
