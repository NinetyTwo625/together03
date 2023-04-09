package com.together.web.dto;

import com.together.domain.comment.Comment;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentDto {

    @NotNull
    private Long imageId;

    @NotBlank
    private String content;
}