package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
//        mapper.createTypeMap(Project.class, ProjectDto.class)
//                .addMappings(p -> p.skip(Cup))
    }

    public Project convertToEntity(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    public ProjectDto convertToDto(Project projectEntity) {
        return mapper.map(projectEntity, ProjectDto.class);
    }
}
