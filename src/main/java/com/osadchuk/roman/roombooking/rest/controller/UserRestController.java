package com.osadchuk.roman.roombooking.rest.controller;

import com.osadchuk.roman.roombooking.entity.User;
import com.osadchuk.roman.roombooking.repository.UserRepository;
import com.osadchuk.roman.roombooking.rest.BasicRestController;
import com.osadchuk.roman.roombooking.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for User API
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController implements BasicRestController<User> {
    private final UserRepository userRepository;

    @Autowired
    public UserRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @PostMapping
    public RestResponse<User> create(@RequestBody User user) {
        return new RestResponse<>(userRepository.save(user));
    }

    @Override
    @GetMapping
    public RestResponse<List<User>> get() {
        return new RestResponse<>(userRepository.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public RestResponse<User> get(@PathVariable long id) {
        return new RestResponse<>(userRepository.findById(id).orElse(null));
    }

    @Override
    @PutMapping
    public RestResponse<User> update(@RequestBody User user) {
        return new RestResponse<>(userRepository.save(user));
    }

    @Override
    @DeleteMapping
    public RestResponse<User> delete(@RequestParam long id) {
        userRepository.deleteById(id);
        return new RestResponse<>();
    }
}
