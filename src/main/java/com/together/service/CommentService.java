package com.together.service;

import com.together.domain.comment.Comment;
import com.together.domain.comment.CommentRepository;
import com.together.domain.image.Image;
import com.together.domain.user.User;
import com.together.domain.user.UserRepository;
import com.together.handler.ex.CustomApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    /* 댓글 작성 */
    @Transactional
    public Comment writeComment(String content, Long imageId, Long userId) {
        Image image = new Image();
        image.setId(imageId);

        User userEntity = userRepository.findById(userId).orElseThrow(() -> {
            return new CustomApiException("찾을 수 없는 사용자입니다.");
        });

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setImage(image);
        comment.setUser(userEntity);

        return commentRepository.save(comment);
    }

    /* 댓글 삭제 */
    @Transactional
    public void deleteComment(Long commentId) {
        try {
            commentRepository.deleteById(commentId);
        } catch(Exception e) {
            throw new CustomApiException(e.getMessage());
        }
    }
}