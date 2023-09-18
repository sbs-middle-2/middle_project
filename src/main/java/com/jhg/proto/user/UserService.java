package com.jhg.proto.user;

import com.jhg.proto.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String password, String email, String name, String nickname, String birthdate, String telecom, String phone) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(name);
        user.setNickname(nickname);
        user.setBirthdate(birthdate);
        user.setTelecom(telecom);
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
}
