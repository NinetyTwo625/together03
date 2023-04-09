package com.together.web;

import com.together.domain.user.User;
import com.together.handler.ex.CustomValidationException;
import com.together.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.together.web.dto.SignupDto;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller	//IoC, 파일을 리턴하는 Controller
public class AuthController {

    private final AuthService authService;

    /* 로그인 페이지 */
    @GetMapping("/auth/signin")
    public String signinForm(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "exception", required = false) String exception,
            Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "auth/signin";
    }

    /* 회원가입 페이지 */
    @GetMapping("/auth/signup")
    public String signupForm() {

        return "auth/signup";
    }

    /* 회원가입 진행 */
    @PostMapping("/auth/signup")
    public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) {

        //SignupDto -> User
        User user = signupDto.toEntity();
        authService.join(user);

        return "auth/signin";
    }
}