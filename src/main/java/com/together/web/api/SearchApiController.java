package com.together.web.api;

import java.util.List;

import com.together.domain.image.Image;
import com.together.domain.user.User;
import com.together.service.ImageService;
import com.together.service.UserService;
import com.together.web.dto.CMRespDto;
import com.together.web.dto.RelativeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SearchApiController {

    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/api/relative/{keyword}")
    public ResponseEntity<?> relative(@PathVariable String keyword) {
        List<User> relativeUserList = userService.userSearch(keyword);
        List<Image> relativeTagList = imageService.imageSearch(keyword);

        RelativeDto relativeDto = new RelativeDto();
        relativeDto.setRelativeUserDto(relativeUserList);
        relativeDto.setRelativeTagDto(relativeTagList);

        return new ResponseEntity<>(new CMRespDto<>(1, "연관 검색어 목록 가져오기 성공", relativeDto), HttpStatus.OK);
    }
}