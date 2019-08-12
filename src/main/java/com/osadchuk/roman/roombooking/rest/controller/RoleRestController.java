package com.osadchuk.roman.roombooking.rest.controller;

import com.osadchuk.roman.roombooking.entity.Role;
import com.osadchuk.roman.roombooking.repository.RoleRepository;
import com.osadchuk.roman.roombooking.rest.BasicRestController;
import com.osadchuk.roman.roombooking.rest.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController for Role API
 */
@RestController
@RequestMapping("/api/roles")
public class RoleRestController implements BasicRestController<Role> {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleRestController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @PostMapping
    public RestResponse<Role> create(@RequestBody Role role) {
        return new RestResponse<>(roleRepository.save(role));
    }

    @Override
    @GetMapping
    public RestResponse<List<Role>> get() {
        return new RestResponse<>(roleRepository.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public RestResponse<Role> get(@PathVariable long id) {
        return new RestResponse<>(roleRepository.findById(id).orElse(null));
    }

    @Override
    @PutMapping
    public RestResponse<Role> update(@RequestBody Role role) {
        return new RestResponse<>(roleRepository.save(role));
    }

    @Override
    @DeleteMapping
    public RestResponse<Role> delete(@RequestParam long id) {
        roleRepository.deleteById(id);
        return new RestResponse<>();
    }
}
