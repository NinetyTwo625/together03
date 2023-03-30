package com.together.web.dto;

import com.together.domain.comment.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {
    private int id;
    @NotBlank
    private String content;

    @NotNull
    private Integer imageId;

    private int userId;
    private String name;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.imageId = comment.getImage().getId();
        this.userId = comment.getUser().getId();
        this.name = comment.getUser().getUsername();
    }
}
