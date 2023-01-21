package com.myfirst.service;

import com.myfirst.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    List<PostDto> getAll();

    PostDto getOneLead(long id);

    PostDto updateOne(PostDto postDto, long id);

    void delete(long id);

    PostDto updateOnelead(long id);


//
//    List <PostDto>getAll(PostDto postDto);
}
