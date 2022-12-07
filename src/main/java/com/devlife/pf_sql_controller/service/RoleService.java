package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.exception.RoleNotFoundException;
import com.devlife.pf_sql_controller.mapper.RoleMapper;
import com.devlife.pf_sql_controller.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper mapper;

    public RoleDto addRole(RoleDto role) {
        final Role saveRole = roleRepository.save(mapper.convertToEntity(role));
        return mapper.convertToDto(saveRole);
    }

    public RoleDto getRole(Long id) {
        final Optional<Role> roleOpt = roleRepository.findById(id);
        final Role role = roleOpt.orElseThrow(() -> new RoleNotFoundException("id : " + id));
        return mapper.convertToDto(role);
    }

    public List<RoleDto> getRolesByUserGroupId(Long userGroupId) {
        final Set<Role> rolesList = roleRepository.findByUserGroupId(userGroupId);
        return rolesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteRoleById(Long id) {
        final Optional<Role> projectTypeOpt = roleRepository.findById(id);
        final Role projectType = projectTypeOpt.orElseThrow(() -> new RoleNotFoundException("id : " + id));
        roleRepository.delete(projectType);
    }

}
