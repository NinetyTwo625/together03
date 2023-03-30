package com.together.web.dto;

import com.together.domain.comment.Comment;
import com.together.domain.image.Image;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageDetailDto {
    private int imageId;
    private String name;
    private String profileImage;
    private String caption;
    private String postImage;
    private int likeCount;
    private boolean likeState;
    private List<CommentDto> commentDto;

    public ImageDetailDto(Image image) {
        this.imageId = image.getId();
        this.name = image.getUser().getName();
        this.profileImage = image.getUser().getProfileImageUrl();
        this.caption = image.getCaption();
        this.postImage = image.getPostImageUrl();
        this.likeCount = image.getLikeCount();
        this.likeState = image.isLikeState();
    }

    public List<CommentDto> commentList(List<Comment> comments) {
        List<CommentDto> commentList = new ArrayList<>();
        for(Comment comment : comments) {
            commentList.add(new CommentDto(comment));
        }

        return commentList;
    }
}
