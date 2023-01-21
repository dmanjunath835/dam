package com.myblog.service.impl;

import com.myblog.payload.PostDto;
import com.myblog.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllLeads(int pagNo, int pageSize,String sortBy,String sortDir);

    PostDto getOneLead(long id);

    PostDto updateOnelead(PostDto postDto, long id);

    void deletelead(long id);
}
