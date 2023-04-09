package com.together.web.dto;

import com.together.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserProfileDto {

    private User user;
    private boolean pageOwnerState;
    private boolean subscribeState;
    private int imageCount;
    private int subscribeCount;
}