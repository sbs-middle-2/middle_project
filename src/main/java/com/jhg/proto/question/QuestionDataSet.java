/*
package com.jhg.proto.question;

import com.jhg.proto.user.SiteUser;
import com.jhg.proto.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class QuestionDataSet {

    private final QuestionService questionService;
    private final UserService userService;

    // 임의의 제목을 생성하는 메서드
    private String generateRandomSubject() {
        // 실제로 사용할 랜덤 제목 생성 로직을 추가하세요.
        // 이 예제에서는 간단한 랜덤 제목을 생성하도록 하겠습니다.
        String[] subjects = {"질문 1", "질문 2", "질문 3", "질문 4", "질문 5"};
        Random random = new Random();
        int index = random.nextInt(subjects.length);
        return subjects[index];
    }

    // 임의의 내용을 생성하는 메서드
    private String generateRandomContent() {
        // 실제로 사용할 랜덤 내용 생성 로직을 추가하세요.
        // 이 예제에서는 간단한 랜덤 내용을 생성하도록 하겠습니다.
        String[] contents = {"내용 1", "내용 2", "내용 3", "내용 4", "내용 5"};
        Random random = new Random();
        int index = random.nextInt(contents.length);
        return contents[index];
    }

    // question 데이터 생성
    public void generateQuestionData() {
        SiteUser user1 = userService.getUser("user1"); // 기존 유저 데이터 가져오기

        // 13개의 랜덤 질문 데이터 생성
        for (int i = 0; i < 13; i++) {
            String subject = generateRandomSubject();
            String content = generateRandomContent();
            Question question = new Question();
            question.setSubject(subject);
            question.setContent(content);
            question.setCreateDate(LocalDateTime.now());
            question.setAuthor(user1); // 작성자를 user1으로 고정
            question.setModifyDate(LocalDateTime.now());

            // questionService.create 메서드를 직접 호출하여 데이터베이스에 저장
            questionService.create(subject, content, user1);
        }
    }
}
*/
