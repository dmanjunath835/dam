package com.myblogapp77.service;

import com.myblogapp77.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId,CommentDto commentDto);
    List<CommentDto> getCommentPostId(long postId);


}
