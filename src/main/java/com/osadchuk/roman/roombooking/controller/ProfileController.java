package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        userService.findByUsernameAsDTO(userDetails.getUsername())
                .ifPresent(user -> model.addAttribute("user", user));
        return "profile";
    }

    @PostMapping
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails, Model model,
                                @ModelAttribute("user") @Valid UserDTO userDTO,
                                BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            userDTO = userService.update(userDTO);
            if (Strings.isNotBlank(userDTO.getError())) {
                model.addAttribute("errorMessage", userDTO.getError());
            } else {
                model.addAttribute("successMessage", "User was updated!");
            }
        }
        return "profile";
    }
}
