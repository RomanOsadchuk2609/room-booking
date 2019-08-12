package com.osadchuk.roman.roombooking.service;

import com.osadchuk.roman.roombooking.entity.User;
import com.osadchuk.roman.roombooking.model.UserDTO;
import com.osadchuk.roman.roombooking.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

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
}
