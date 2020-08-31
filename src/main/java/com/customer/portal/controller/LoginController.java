package com.customer.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/")
    public String root() {
        return "login";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

}
