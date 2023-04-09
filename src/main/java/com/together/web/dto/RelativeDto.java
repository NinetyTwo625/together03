package com.together.web.dto;

import com.together.domain.image.Image;
import com.together.domain.user.User;
import lombok.Data;

import java.util.List;

@Data
public class RelativeDto {

    private List<User> relativeUserDto;
    private List<Image> relativeTagDto;
}