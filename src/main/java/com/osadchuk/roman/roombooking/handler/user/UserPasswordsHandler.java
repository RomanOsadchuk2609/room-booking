package com.osadchuk.roman.roombooking.handler.user;

import com.osadchuk.roman.roombooking.model.UserDTO;
import org.springframework.stereotype.Component;

/**
 * Handler intended to check equality of entered passwords in process of registration
 */
@Component
public class UserPasswordsHandler extends UserDTOHandler{
    @Override
    public boolean isValid(UserDTO userDTO) {
        if (userDTO.getPassword().equals(userDTO.getConfirmPassword())){
            return true;
        } else {
            setMessage("Passwords does not match! ");
            return false;
        }
    }
}
