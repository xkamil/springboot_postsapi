package com.canx.postapp.service;

import com.canx.postapp.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO addPost(PostDTO post);

    List<PostDTO> getPosts();

    PostDTO findPost(Long id);
}
