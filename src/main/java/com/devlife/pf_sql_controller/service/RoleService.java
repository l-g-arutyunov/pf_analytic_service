package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Long addRole(Role role) {
        Role saveRole = roleRepository.save(role);
        if (saveRole != null) {
            return saveRole.getId();
        }
        return null;
    }

    public Role getRole(Long id) {
        Role role = roleRepository.getById(id);
        return role;
    }

    public List<Role> getAllRoles() {
        List<Role> rolesList = roleRepository.findAll();
        return rolesList;
    }

    public Boolean deleteRoleById(Long id) {
        roleRepository.deleteById(id);
        return !roleRepository.existsById(id);
    }
}
