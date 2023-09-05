package com.jhg.proto.member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime; // LocalDateTime 추가

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime regDate; // 등록일시

    private LocalDateTime updateDate; // 수정일시

    @Column(unique = true)
    private String loginId; // 로그인 아이디

    private String loginPw; // 비밀번호

    private int authLevel; // 권한 레벨

    private String name; // 이름

    private String nickname; // 닉네임

    private String cellphoneNo; // 휴대폰 번호

    @Column(unique = true)
    private String email; // 이메일

    private int delStatus; // 탈퇴 여부 (0 또는 1)

    private LocalDateTime delDate; // 탈퇴 날짜
}
