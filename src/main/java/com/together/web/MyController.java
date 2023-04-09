package com.together.web;

import com.together.domain.image.Image;
import com.together.domain.user.User;
import com.together.service.ImageService;
import com.together.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MyController {

    private final UserService userService;
    private final ImageService imageService;

    /* 검색 */
    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<User> userSearchList = userService.userSearch(keyword);
        List<Image> imageSearchList = imageService.imageSearch(keyword);

        model.addAttribute("users", userSearchList);
        model.addAttribute("images", imageSearchList);

        return "search";
    }
}