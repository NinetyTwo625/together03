package com.together.web.api;

import com.together.config.auth.PrincipalDetails;
import com.together.domain.image.Image;
import com.together.handler.ex.CustomValidationException;
import com.together.service.ImageService;
import com.together.service.LikesService;
import com.together.web.dto.CMRespDto;
import com.together.web.dto.ImageUploadDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    private final ImageService imageService;
    private final LikesService likesService;

    /* 이미지 업로드 */
    @PostMapping("/api/upload")
    public ResponseEntity<?> imageUpload(ImageUploadDto imageUploadDto,
                                         @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if(imageUploadDto.getFile().isEmpty()) {
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.", null);
        }

        imageService.upload(imageUploadDto, principalDetails);

        return new ResponseEntity<>(new CMRespDto<>(1, "이미지 업로드 성공", principalDetails.getUser().getId()), HttpStatus.OK);
    }

    /* 스토리 페이지 */
    @GetMapping("/api/image")
    public ResponseEntity<?> story(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                   @PageableDefault(size = 3) Pageable pageable) {
        Page<Image> images = imageService.story(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(new CMRespDto<>(1, "이미지 스토리 불러오기 성공", images), HttpStatus.OK);
    }

    /* 게시글 좋아요 */
    @PostMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> likes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.likes(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 성공", null), HttpStatus.CREATED);
    }

    /* 게시글 좋아요 해제 */
    @DeleteMapping("/api/image/{imageId}/likes")
    public ResponseEntity<?> unLikes(@PathVariable Long imageId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        likesService.unLikes(imageId, principalDetails.getUser().getId());
        return new ResponseEntity<>(new CMRespDto<>(1, "좋아요 취소 성공", null), HttpStatus.OK);
    }
}