package com.osadchuk.roman.roombooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller for main url-mappings
 */
@Controller
public class MainController {

    @GetMapping("/")
    public RedirectView booking(Model model) {
        return new RedirectView("/booking");
    }

    @GetMapping("/admin")
    public RedirectView admin(Model model) {
        return new RedirectView("/admin/rooms");
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
