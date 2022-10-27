package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.entity.Project;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {
    private final ModelMapper mapper;

    public ProjectMapper(ModelMapper mapper) {
        this.mapper = mapper;
        //TODO Зачем?
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
