package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectRoleMapper {
    private final ModelMapper mapper;
    
    public ProjectRole convertToEntity(ProjectRoleDto projectRoleDto) {
        return mapper.map(projectRoleDto, ProjectRole.class);
    }

    public ProjectRoleDto convertToDto(ProjectRole projectRoleEntity) {
        return mapper.map(projectRoleEntity, ProjectRoleDto.class);
    }
}
