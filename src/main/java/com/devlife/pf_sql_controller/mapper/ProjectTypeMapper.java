package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.entity.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectTypeMapper {
    private final ModelMapper mapper;

    public ProjectType convertToEntity(ProjectTypeDto projectTypeDto) {
        return mapper.map(projectTypeDto, ProjectType.class);
    }

    public ProjectTypeDto convertToDto(ProjectType projectTypeEntity) {
        return mapper.map(projectTypeEntity, ProjectTypeDto.class);
    }
}
