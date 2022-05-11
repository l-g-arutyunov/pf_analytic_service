package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper mapper;

    public Role convertToEntity(RoleDto roleDto) {
        return mapper.map(roleDto, Role.class);
    }

    public RoleDto convertToDto(Role roleEntity) {
        return mapper.map(roleEntity, RoleDto.class);
    }
}
