package com.life.service;

import com.life.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto saveOne(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);
}
