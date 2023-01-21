package com.app.service;

import com.app.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPostId(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo,int pageSize,String sortBy,String sortDir);

    PostDto getFindId( long id);

    PostDto updateOnePost(PostDto postDto, long id);

    void delete(long id);
}
