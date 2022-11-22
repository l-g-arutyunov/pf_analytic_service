package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectRoleMapper {
    private final ModelMapper mapper;

    public ProjectRoleMapper(ModelMapper mapper) {
        this.mapper = mapper;
        addMappingAddProjectMemberReqToEntity();
    }

    public ProjectRole convertToEntity(ProjectRoleDto projectRoleDto) {
        return mapper.map(projectRoleDto, ProjectRole.class);
    }

    public ProjectRoleDto convertToDto(ProjectRole projectRoleEntity) {
        return mapper.map(projectRoleEntity, ProjectRoleDto.class);
    }

    public Set<ProjectRole> convertAddProjectMemberReqToEntity(Project project, Map<User, Set<AddProjectMemberReq>> addProjectMemberReqMap) {
        return addProjectMemberReqMap.entrySet().stream()
                .flatMap(entry -> {
                    User user = entry.getKey();
                    return entry.getValue().stream()
                            .map(item -> {
                                ProjectRole projectRole = mapper.map(item, ProjectRole.class);
                                projectRole.setProject(project);
                                projectRole.setUser(user);
                                return projectRole;
                            });
                }).collect(Collectors.toSet());
    }

    private void addMappingAddProjectMemberReqToEntity() {
        mapper.addMappings(new PropertyMap<AddProjectMemberReq, ProjectRole>() {
            @Override
            protected void configure() {
                map().setRole(Role.builder().id(source.getRoleId()).build());
            }
        });
    }
}
