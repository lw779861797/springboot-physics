package com.physics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OtherController {
    @RequestMapping("/notgo")
    public String returnundevelopde() {
        return "undeveloped";
    }
}
