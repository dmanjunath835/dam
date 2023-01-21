package com.myblog.service;

import com.myblog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAll(int pageNo,int pageSize);

    PostDto getOneLead(long id);

    PostDto updateOne(PostDto postDto, long id);

    void deleteOne(long id);
}
