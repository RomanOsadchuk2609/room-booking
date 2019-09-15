package com.osadchuk.roman.roombooking.service;

import com.osadchuk.roman.roombooking.entity.User;
import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

/**
 * Service layer for operations with User entity
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public boolean isUserAlreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User registerUser(UserDTO userDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEnabled(true);
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(new HashSet<>(Collections.singletonList(roleService.getUserRole())));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<UserDTO> findByUsernameAsDTO(String username) {
        return Optional.of(convertIntoDTO(userRepository.findByUsername(username).get()));
    }

    private UserDTO convertIntoDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public User update(User user) {
        User oldUser = findByUsername(user.getUsername()).orElse(new User());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setPhoneNumber(user.getPhoneNumber());
        oldUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User oldUser = findByUsername(userDTO.getUsername()).orElse(new User());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(userDTO.getPassword(), oldUser.getPassword())) {
            String newPassword = userDTO.getNewPassword();
            if (Strings.isBlank(newPassword)) {
                oldUser.setFirstName(userDTO.getFirstName());
                oldUser.setLastName(userDTO.getLastName());
                oldUser.setPhoneNumber(userDTO.getPhoneNumber());
                oldUser = userRepository.save(oldUser);
                return convertIntoDTO(oldUser);
            } else {
                if (Strings.isNotBlank(userDTO.getConfirmPassword()) && newPassword.equals(userDTO.getConfirmPassword())) {
                    oldUser.setFirstName(userDTO.getFirstName());
                    oldUser.setLastName(userDTO.getLastName());
                    oldUser.setPhoneNumber(userDTO.getPhoneNumber());
                    oldUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.getNewPassword()));
                    oldUser = userRepository.save(oldUser);
                    return convertIntoDTO(oldUser);
                } else {
                    userDTO.setError("Passwords does not match!");
                    return userDTO;
                }
            }
        } else {
            userDTO.setError("Incorrect password!");
            return userDTO;
        }
    }
}
