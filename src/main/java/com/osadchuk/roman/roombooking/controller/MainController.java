package com.osadchuk.roman.roombooking.controller;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for main url-mappings
 */
@Controller
public class MainController {

    @GetMapping("/admin")
    public String admin(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("user", context.getAuthentication().getName());
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        SecurityContext context = SecurityContextHolder.getContext();
        model.addAttribute("user", context.getAuthentication().getName());
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
