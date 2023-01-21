package com.mylife.service;

import com.mylife.payload.PostDto;
import com.mylife.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo, int pageSize, String sortBy,String sortDir);                  ;

    PostDto getOneLead(long id);

    PostDto updateOnePost(PostDto postDto, long id);

    void deleteOne(long id);
}
