package com.jhg.proto;

import com.jhg.proto.question.QuestionService;
import com.jhg.proto.user.SiteUser;
import com.jhg.proto.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
public class DB_Data_Set {

    private final UserService userService;
    private final QuestionService questionService;

    @Autowired
    public DB_Data_Set(UserService userService, QuestionService questionService) {
        this.userService = userService;
        this.questionService = questionService;
    }

    @PostConstruct
    public void initializeData() {
        // Insert admin user
        userService.create("admin", "admin", "admin@admin.com", "관리자", "program_manager", "0000-00-00", "KT", "010-0000-0000");

        // Insert user1
        SiteUser user1 = userService.create("user1", "user1", "user1@user1.com", "user1name", "user1nickname", "1111-11-11", "KT", "010-1111-1111");

        // boardId 1, 2, 3에 대해 13개의 데이터 생성
        Random random = new Random();
        for (int boardId = 1; boardId <= 3; boardId++) {
            for (int i = 1; i <= 13; i++) {
                String subject = generateRandomSubject();
                String content = generateRandomContent();

                // create 메서드를 호출하여 데이터 생성
                questionService.create(subject, content, user1, boardId);
            }
        }
    }

    // 랜덤한 제목 생성 (예시)
    private String generateRandomSubject() {
        String[] subjects = {
                "질문 제목 1",
                "질문 제목 2",
                // 더 많은 제목 추가
        };
        int randomIndex = new Random().nextInt(subjects.length);
        return subjects[randomIndex];
    }

    // 랜덤한 내용 생성 (예시)
    private String generateRandomContent() {
        String[] contents = {
                "질문 내용 1",
                "질문 내용 2",
                // 더 많은 내용 추가
        };
        int randomIndex = new Random().nextInt(contents.length);
        return contents[randomIndex];
    }
}
