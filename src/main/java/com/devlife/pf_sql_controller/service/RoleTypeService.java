package com.devlife.pf_sql_controller.service;


import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.entity.RoleType;
import com.devlife.pf_sql_controller.mapper.RoleTypeMapper;
import com.devlife.pf_sql_controller.repository.RoleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class RoleTypeService {
    private final RoleTypeRepository roleTypeRepository;
    private final RoleTypeMapper mapper;


    public Long addRoleType(RoleTypeDto roleType) {
        RoleType saveRoleType = roleTypeRepository.save(mapper.convertToEntity(roleType));
        if (saveRoleType != null) {
            return saveRoleType.getId();
        }
            return null;
    }

    public RoleTypeDto getRoleType(Long id) {
        RoleType roleType = roleTypeRepository.getById(id);
        return mapper.convertToDto(roleType);
    }

    public List<RoleTypeDto> getAllRoleTypes() {
        List<RoleType> roleTypesList = roleTypeRepository.findAll();
        return roleTypesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteRoleTypeById(Long id) {
        roleTypeRepository.deleteById(id);
        return !roleTypeRepository.existsById(id);
    }








}
