package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Controller for user registration
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping
    public String registration(Model model, @ModelAttribute("user") @Valid UserDTO userDTO,
                               BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            if (!userService.isUserAlreadyExists(userDTO.getUsername())) {
                if (userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                    userService.registerUser(userDTO);
                    model.addAttribute("successMessage", "User was registered");
                } else {
                    model.addAttribute("errorMessage", "Password doesn't matches");
                }
            } else {
                model.addAttribute("errorMessage", "User with username " + userDTO.getUsername() + " already exists");
            }
        }
        return "/registration";
    }
}
