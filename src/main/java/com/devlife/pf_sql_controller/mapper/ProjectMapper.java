package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.UpdateProjectByProjectIdReq;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.entity.UserGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final ModelMapper mapper;

    public Project convertToEntity(ProjectDto projectDto) {
        return mapper.map(projectDto, Project.class);
    }

    public ProjectDto convertToDto(Project projectEntity) {
        return mapper.map(projectEntity, ProjectDto.class);
    }

    public Project convertUpdateProjectByProjectIdReqToEntity(UpdateProjectByProjectIdReq updateProjectByProjectIdReq, Long projectId, UserGroup userGroup) {
        Project project = mapper.map(updateProjectByProjectIdReq, Project.class);
        project.setId(projectId);
        project.setUserGroup(userGroup);
        return project;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(UpdateProjectByProjectIdReq.class, Project.class)
                .addMappings(m -> m.skip(Project::setEmployer))
                .addMappings(m -> m.skip(Project::setProjectType))
                .setPostConverter(mappingContext -> {
                    UpdateProjectByProjectIdReq source = mappingContext.getSource();
                    Project destination = mappingContext.getDestination();
                    destination.setEmployer(source.getEmployerId() != null ? Employer.builder().id(source.getEmployerId()).build() : null);
                    destination.setProjectType(source.getProjectTypeId() != null ? ProjectType.builder().id(source.getProjectTypeId()).build() : null);
                    return destination;
                });
    }
}
