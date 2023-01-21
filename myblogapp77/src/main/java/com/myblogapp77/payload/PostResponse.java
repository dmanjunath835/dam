package com.myblogapp77.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
 List<PostDto> content;
private int pageNo;
private int pageSize;
private int totalPages;
private long totalElements;
private boolean last;



}
