package com.osadchuk.roman.roombooking.model;

import com.osadchuk.roman.roombooking.validator.name.ValidName;
import com.osadchuk.roman.roombooking.validator.password.ValidNewPassword;
import com.osadchuk.roman.roombooking.validator.password.ValidPassword;
import com.osadchuk.roman.roombooking.validator.phoneNumber.ValidPhoneNumber;
import com.osadchuk.roman.roombooking.validator.username.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object fro user entity representation with custom validation using on registration page
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    @ValidUsername
    private String username;

    @ValidName
    private String firstName;

    @ValidName
    private String lastName;

    @ValidPhoneNumber
    private String phoneNumber;

    @ValidPassword
    private String password;

    private String confirmPassword;

    @ValidNewPassword
    private String newPassword;

    private String error;
}
