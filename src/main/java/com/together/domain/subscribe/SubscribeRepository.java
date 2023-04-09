package com.together.domain.subscribe;

import com.together.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    /* 구독하기 */
    @Modifying	//INSERT, DELETE, UPDATE를 native query로 작성하려면 해당 어노테이션이 필요
    @Query(value = "INSERT INTO subscribe(from_user_id, to_user_id, create_date) VALUES(:from_user_id, :to_user_id, now())", nativeQuery = true)
    void subscribe(Long from_user_id, Long to_user_id);

    /* 구독취소 */
    @Modifying
    @Query(value = "DELETE FROM subscribe WHERE from_user_id = :from_user_id AND to_user_id = :to_user_id", nativeQuery = true)
    void unSubscribe(Long from_user_id, Long to_user_id);

    /* 구독상태 */
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :principal_id AND to_user_id = :page_user_id", nativeQuery = true)
    int subscribeState(Long principal_id, Long page_user_id);

    /* 구독개수 */
    @Query(value = "SELECT COUNT(*) FROM subscribe WHERE from_user_id = :page_user_id", nativeQuery = true)
    int subscribeCount(Long page_user_id);

    /* 내가 구독한 사용자 */
    @Query(value = "SELECT to_user_id FROM subscribe WHERE from_user_id = :principal_id", nativeQuery = true)
    List<Long> findSubscribeFrom(Long principal_id);

    /* 나를 구독한 사용자 */
    @Query(value = "SELECT from_user_id FROM subscribe WHERE to_user_id = :principal_id", nativeQuery = true)
    List<Long> findSubscribeTo(Long principal_id);
}