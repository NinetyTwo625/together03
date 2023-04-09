package com.together.domain.user;

import com.together.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>{

    User findByUsername(String username);
    List<User> findByNameContaining(String keyword);

    @Query(value = "SELECT * FROM user WHERE id = :id", nativeQuery = true)
    User findByUserId(Long id);
}