package com.osadchuk.roman.roombooking.controller;

import com.osadchuk.roman.roombooking.handler.user.UserDTOHandler;
import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.service.UserService;
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
//TODO: CHAIN OF RESPONSIBILITY

/**
 * Controller for user registration
 */
@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;

    private final UserDTOHandler userExistsHandler;

    private final UserDTOHandler userPasswordsHandler;

    public RegistrationController(UserDTOHandler userPasswordsHandler, UserDTOHandler userExistsHandler,
                                  UserService userService) {
        this.userPasswordsHandler = userPasswordsHandler;
        this.userExistsHandler = userExistsHandler;
        this.userService = userService;

        userExistsHandler.setNext(userPasswordsHandler);
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping
    public String registration(Model model, @ModelAttribute("user") @Valid UserDTO userDTO,
                               BindingResult result, WebRequest request, Errors errors) {
        if (!result.hasErrors()) {
            if (userExistsHandler.handle(userDTO)) {
                userService.registerUser(userDTO);
                model.addAttribute("successMessage", "User was registered");
            } else {
                model.addAttribute("errorMessage", userExistsHandler.getMessage());
            }
        }
        return "/registration";
    }
}
