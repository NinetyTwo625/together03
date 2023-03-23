package com.together.service;

import com.together.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.together.domain.user.User;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service // 1.IoC 2.트랜잭션 관리
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public User 회원가입(User user) {
        String rawPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        user.setRole("ROLE_USER");
        user.setPassword(encPassword);
        //회원가입 진행
        User userEntity = userRepository.save(user);
        return userEntity;
    }
}