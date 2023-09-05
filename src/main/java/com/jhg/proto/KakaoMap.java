package com.jhg.proto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class KakaoMap {

    @GetMapping("/kakao/map")
    public String kakaoMap() {
        return "kakaoMap";
    }
}
