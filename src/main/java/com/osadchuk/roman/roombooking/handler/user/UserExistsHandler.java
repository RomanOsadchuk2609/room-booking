package com.osadchuk.roman.roombooking.handler.user;

import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.service.UserService;
import org.springframework.stereotype.Component;

/**
 * Handler intended to check existence of the user with same username
 */
@Component
public class UserExistsHandler extends UserDTOHandler {

    private final UserService userService;

    public UserExistsHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(UserDTO user) {
        if (!userService.isUserAlreadyExists(user.getUsername())) {
            return true;
        } else {
            setMessage("User " + user.getUsername() + " already exists! ");
            return false;
        }
    }
}
