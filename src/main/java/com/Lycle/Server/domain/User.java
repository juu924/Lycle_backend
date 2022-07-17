package com.Lycle.Server.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 50)
    private String password;

    @Column(nullable = false, length = 100)
    private String nickname;

    @Column(nullable = true)
    private Long sharedId;

    @Column(nullable = true)
    private Long totalTime;

    @Builder
    public User(Long id, String email,String password, String nickname, Long sharedId, Long totalTime){
        this.id = id;
        this.email =email;
        this.password = password;
        this.nickname = nickname;
        this.sharedId = sharedId;
        this.totalTime = totalTime;
    }
}
