package com.canx.postapp.dto;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class CommentDTO {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    @NotNull
    private String text;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostDTO post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
