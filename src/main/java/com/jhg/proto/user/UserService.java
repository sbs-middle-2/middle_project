package com.jhg.proto.user;

import com.jhg.proto.DataNotFoundException;
import com.jhg.proto.answer.Answer;
import com.jhg.proto.answer.AnswerRepository;
import com.jhg.proto.question.Question;
import com.jhg.proto.question.QuestionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    public SiteUser create(String username, String password, String email, String name, String nickname, String birthdate,  String phone) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setNickname(nickname);
        user.setBirthdate(birthdate);
        user.setPhone(phone);
        this.userRepository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = this.userRepository.findByusername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("siteuser not found");
        }
    }

    public boolean isUsernameAvailable(String username) {
        boolean isAvailable = !userRepository.existsByUsername(username);
        return isAvailable;
    }

    // 수정된 부분: updateUser 메서드 추가
    public void updateUser(SiteUser updatedUser) throws DataIntegrityViolationException, UsernameNotFoundException {
        // 기존 사용자 정보 가져오기
        Optional<SiteUser> optionalUser = userRepository.findByusername(updatedUser.getUsername());

        if (optionalUser.isPresent()) {
            SiteUser currentUser = optionalUser.get();

            // 업데이트된 정보로 사용자 업데이트
            currentUser.setNickname(updatedUser.getNickname());
            currentUser.setPhone(updatedUser.getPhone());
            currentUser.setEmail(updatedUser.getEmail());

            userRepository.save(currentUser);
        } else {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
    }

    public void delete(SiteUser siteUser) {
        // 해당 사용자와 연결된 answer 레코드 삭제
        List<Answer> userAnswers = answerRepository.findByAuthor(siteUser);
        answerRepository.deleteAll(userAnswers);

        // 해당 사용자와 연결된 question 레코드 삭제
        List<Question> userQuestions = questionRepository.findByAuthor(siteUser);
        questionRepository.deleteAll(userQuestions);

        // 사용자 삭제
        this.userRepository.delete(siteUser);
    }


    public boolean isCorrectPassword(String username, String password) {
        SiteUser user = getUser(username);
        String actualPassword = user.getPassword();
        return passwordEncoder.matches(password, actualPassword);
    }

    public void updatePassword(String username, String newPassword) {
        SiteUser user = getUser(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // 추가: 사용자와 관련된 Answer 엔티티 삭제
    public void deleteUserWithAnswers(Long userId) {
        // 사용자 삭제 전에 해당 사용자와 관련된 Answer 엔티티 삭제
        List<Answer> userAnswers = answerRepository.findByAuthorId(userId);
        answerRepository.deleteAll(userAnswers);

        // 사용자 삭제
        userRepository.deleteById(userId);
    }
}