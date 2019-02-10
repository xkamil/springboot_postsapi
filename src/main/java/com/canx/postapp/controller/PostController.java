package com.canx.postapp.controller;

import com.canx.postapp.dto.PostDTO;
import com.canx.postapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostDTO> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id")
    public PostDTO findPost(@PathVariable("id") Long id) {
        return postService.findPost(id);
    }

    @PostMapping
    public PostDTO addPost(@RequestBody @Valid PostDTO post) {
        return postService.addPost(post);
    }
}
