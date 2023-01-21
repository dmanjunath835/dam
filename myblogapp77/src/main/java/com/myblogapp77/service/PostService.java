package com.myblogapp77.service;

import com.myblogapp77.payload.PostDto;
import com.myblogapp77.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

}
