package com.canx.postapp.service;

import com.canx.postapp.dto.PostDTO;
import com.canx.postapp.exception.NotFoundException;
import com.canx.postapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService  {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostDTO addPost(PostDTO post) {
        return postRepository.save(post);
    }

    public List<PostDTO> getPosts() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public PostDTO findPost(Long id) {
        String errorMessage = String.format("Post with id: %s not found", id);
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(errorMessage));
    }
}
