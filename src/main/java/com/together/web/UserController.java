package com.together.web;

import com.together.config.auth.PrincipalDetails;
import com.together.domain.user.User;
import com.together.service.UserService;
import com.together.web.dto.UserProfileDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    /* 사용자 프로필 페이지 */
    @GetMapping("/user/{pageUserId}")
    public String profile(@PathVariable Long pageUserId, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        UserProfileDto userProfileDto = userService.profile(pageUserId, principalDetails.getUser().getId());
        model.addAttribute("dto", userProfileDto);

        return "user/profile";
    }

    /* 회원정보 수정 페이지 */
    @GetMapping("/user/{id}/update")
    public String update(@PathVariable Long id) {
        return "user/update";
    }
}