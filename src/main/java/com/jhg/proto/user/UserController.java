package com.jhg.proto.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup_form";
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(),
                    userCreateForm.getPassword1(), userCreateForm.getEmail(),
                    userCreateForm.getName(), userCreateForm.getNickname(),
                    userCreateForm.getBirthdate(), userCreateForm.getTelecom(),
                    userCreateForm.getPhone());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }


    @PostMapping("/login")
    public String loginSuccess() {
        // 로그인 성공 시 처리
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        // 로그아웃 처리
        return "redirect:/";
    }



    // 마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String mypage(Principal principal, Model model) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        model.addAttribute("siteUser", siteUser);
        return "mypage_form";
    }

    // 마이페이지
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage_exit")
    public String mypage_exit(Principal principal, Model model) {
        SiteUser siteUser = this.userService.getUser(principal.getName());
        model.addAttribute("siteUser", siteUser);
        return "mypage_secession";
    }



    // 아이디 중복 체크
    @GetMapping("/checkUsername/{username}")
    public ResponseEntity<Map<String, Boolean>> checkUsernameAvailability(@PathVariable String username) {
        // 아이디 중복 체크를 위한 서비스 메서드 호출
        boolean isUsernameTaken = userRepository.existsByUsername(username);
        Map<String, Boolean> response = new HashMap<>();
        response.put("available", !isUsernameTaken);
        return ResponseEntity.ok(response);
    }
}