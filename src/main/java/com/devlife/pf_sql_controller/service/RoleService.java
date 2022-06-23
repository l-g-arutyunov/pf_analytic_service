package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.mapper.RoleMapper;
import com.devlife.pf_sql_controller.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    public Long addRole(RoleDto role) {
        Role saveRole = roleRepository.save(mapper.convertToEntity(role));
        if (saveRole != null) {
            return saveRole.getId();
        }
        return null;
    }

    public RoleDto getRole(Long id) {
        Role role = roleRepository.getById(id);
        return mapper.convertToDto(role);
    }

    public List<RoleDto> getAllRoles() {
        List<Role> rolesList = roleRepository.findAll();
        return rolesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteRoleById(Long id) {
        roleRepository.deleteById(id);
        return !roleRepository.existsById(id);
    }
}
