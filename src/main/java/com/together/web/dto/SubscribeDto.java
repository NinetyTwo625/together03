package com.together.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscribeDto {

    private BigInteger id;
    private String username;
    private String profile_image_url;
    private BigInteger subscribeState;
    private BigInteger equalUserState;
}