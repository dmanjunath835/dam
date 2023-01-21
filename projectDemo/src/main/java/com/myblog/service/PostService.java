package com.myblog.service;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAll(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getOneLead(long id);

    PostDto updateOne(long id, PostDto postDto);
}
