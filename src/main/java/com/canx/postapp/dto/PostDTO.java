package com.canx.postapp.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class PostDTO {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @NotEmpty
    private String ttile;

    @NotNull
    @NotEmpty
    private String text;

    @OneToMany(mappedBy = "post")
    private List<CommentDTO> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTtile() {
        return ttile;
    }

    public void setTtile(String ttile) {
        this.ttile = ttile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
