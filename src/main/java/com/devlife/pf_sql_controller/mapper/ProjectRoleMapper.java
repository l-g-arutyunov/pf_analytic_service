package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import org.modelmapper.PropertyMap;
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

    public ProjectRole convertFromAddProjectMemberReqToEntity(Project project, AddProjectMemberReq addProjectMemberReq) {
        mapper.addMappings(new PropertyMap<AddProjectMemberReq, ProjectRole>() {
            @Override
            protected void configure() {
                map().setProject(project);
                map().setRole();
                map().setUser();
//                map().setRoleLevel();
//                map().setStartDate();
                // descr
                map().setRoleLevel();
            }
        })
    }
}
