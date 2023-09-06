package com.jhg.proto.question;

import com.jhg.proto.answer.Answer;
import com.jhg.proto.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity // question 테이블
public class Question {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(length = 200) // VARCHAR(200)
    private String subject;

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private LocalDateTime createDate;

    // mappedBy : Answer 클래스의 question 변수 이름을 적어야 합니다.
    // CascadeType.REMOVE를 하면 Question을 삭제할 때 답변도 함께 삭제됩니다.
    // OneToMany는 테이블의 칼럼으로 생성되지 않습니다.
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;

    @ManyToMany
    Set<SiteUser> voter;
}