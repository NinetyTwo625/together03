package com.together.web.api;

import com.together.config.auth.PrincipalDetails;
import com.together.service.SubscribeService;
import com.together.web.dto.CMRespDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SubscribeApiController {

    private final SubscribeService subscribeService;

    /* 구독하기 */
    @PostMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.subs(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "구독하기 완료", null), HttpStatus.OK);
    }

    /* 구독취소 */
    @DeleteMapping("/api/subscribe/{toUserId}")
    public ResponseEntity<?> unSubscribe(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long toUserId) {
        subscribeService.unSubs(principalDetails.getUser().getId(), toUserId);

        return new ResponseEntity<>(new CMRespDto<>(1, "구독취소 완료", null), HttpStatus.OK);
    }
}