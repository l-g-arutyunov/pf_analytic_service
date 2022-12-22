package com.devlife.pf_sql_controller.service;


import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.entity.RoleType;
import com.devlife.pf_sql_controller.exception.RoleTypeNotFoundException;
import com.devlife.pf_sql_controller.mapper.RoleTypeMapper;
import com.devlife.pf_sql_controller.repository.RoleTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleTypeService {
    private final RoleTypeRepository roleTypeRepository;
    private final RoleTypeMapper mapper;


    public RoleTypeDto addRoleType(RoleTypeDto RoleType) {
        RoleType saveRoleType = roleTypeRepository.save(mapper.convertToEntity(RoleType));
        return mapper.convertToDto(saveRoleType);
    }

    public RoleTypeDto getRoleType(Long id) {
        final Optional<RoleType> RoleTypeOpt = roleTypeRepository.findById(id);
        final RoleType RoleType = RoleTypeOpt.orElseThrow(() -> new RoleTypeNotFoundException("id : " + id));
        return mapper.convertToDto(RoleType);
    }

    public List<RoleTypeDto> getAllRoleTypes() {
        final List<RoleType> RoleTypesList = roleTypeRepository.findAll();
        return RoleTypesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteRoleTypeById(Long id) {
        final Optional<RoleType> RoleTypeOpt = roleTypeRepository.findById(id);
        final RoleType RoleType = RoleTypeOpt.orElseThrow(() -> new RoleTypeNotFoundException("id : " + id));
        roleTypeRepository.delete(RoleType);
    }
}
