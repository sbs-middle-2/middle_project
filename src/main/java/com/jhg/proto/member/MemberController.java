package com.jhg.proto.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
    @GetMapping("/join")
    public String showJoin(Model model) {


        return "memberJoin";
    }
}
