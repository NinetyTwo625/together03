package com.together.domain.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long>{

    /* 게시글 좋아요 */
    @Modifying
    @Query(value = "INSERT INTO likes(image_id, user_id, create_date) VALUES(:image_id, :principal_id, now())", nativeQuery = true)
    int likes(Long image_id, Long principal_id);

    /* 게시글 좋아요 해제 */
    @Modifying
    @Query(value = "DELETE FROM likes WHERE image_id = :image_id AND user_id = :principal_id", nativeQuery = true)
    int unLikes(Long image_id, Long principal_id);
}
