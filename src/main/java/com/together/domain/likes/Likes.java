package com.together.domain.likes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.together.domain.image.Image;
import com.together.domain.user.User;
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
                        name = "likes_uk",
                        columnNames = {"image_id", "user_id"}
                )
        }
)
@Entity
public class Likes {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties({"images"})
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @JoinColumn(name = "image_id")
    @ManyToOne
    private Image image;

    private LocalDateTime create_date;
}