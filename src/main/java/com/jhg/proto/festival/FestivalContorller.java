package com.jhg.proto.festival;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FestivalContorller {
    @GetMapping("/festivalList")
    public String showFestival() {
        return "/festivalList";
    }
}
