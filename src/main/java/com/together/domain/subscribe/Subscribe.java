package com.together.domain.subscribe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.together.domain.user.User;
import com.together.domain.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(	//데이터베이스에서 두 개의 컬럼에 대해 unique 제약조건 설정
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "subscribe_uk",
                        columnNames = {"from_user_id", "to_user_id"}
                )
        }
)
@Entity
public class Subscribe {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User from_user;

    @ManyToOne
    private User to_user;

    private LocalDateTime create_date;

    @PrePersist
    public void createDate() {
        create_date = LocalDateTime.now();
    }
}
