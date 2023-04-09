package com.together.web;

import com.together.config.auth.PrincipalDetails;
import com.together.domain.image.Image;
import com.together.domain.subscribe.SubscribeRepository;
import com.together.domain.user.User;
import com.together.domain.user.UserRepository;
import com.together.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ImageController {

    private final ImageService imageService;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    /* 스토리 페이지 */
    @GetMapping({"/", "/image/story"})
    public String story(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        List<Long> subList = subscribeRepository.findSubscribeFrom(principalDetails.getUser().getId());
        List<User> subUserList = new ArrayList<User>();

        /* 현재 로그인한 사용자가 구독한 사용자 목록 */
        for (Long id : subList) {
            subUserList.add(userRepository.findByUserId(id));
        }

        model.addAttribute("subUserList", subUserList);

        return "image/story";
    }

    /* 인기 페이지 */
    @GetMapping("/image/popular")
    public String popular(Model model) {
        List<Image> images = imageService.popular();
        model.addAttribute("images", images);

        return "image/popular";
    }

    /* 이미지 업로드 페이지 */
    @GetMapping("/image/upload")
    public String upload() {
        return "image/upload";
    }
}