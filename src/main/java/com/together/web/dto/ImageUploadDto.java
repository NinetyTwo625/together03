package com.together.web.dto;

import com.together.domain.image.Image;
import com.together.domain.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ImageUploadDto {

    private MultipartFile file;
    private String caption;
    private List<String> hashtagList;

    public Image toEntity(User user, String post_image_url) {
        String hashtag = "";
        for(String str : hashtagList) {
            hashtag += str + ",";
        }

        return Image.builder()
                .user(user)
                .post_image_url(post_image_url)
                .caption(caption)
                .hashtag(hashtag)
                .build();
    }
}