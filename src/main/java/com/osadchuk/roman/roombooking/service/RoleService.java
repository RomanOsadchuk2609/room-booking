package com.osadchuk.roman.roombooking.service;

import com.osadchuk.roman.roombooking.entity.Role;
import com.osadchuk.roman.roombooking.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for operations with Role entity
 */
@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getUserRole() {
        return roleRepository.findByName("USER").orElse(null);
    }
}
