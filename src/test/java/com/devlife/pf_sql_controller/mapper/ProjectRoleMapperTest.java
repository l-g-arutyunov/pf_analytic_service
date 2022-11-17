package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProjectRole Mapper Tests")
class ProjectRoleMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    ProjectRoleMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО ProjectRole в Entity")
    void convertToEntityTest_OK() {
        User refUser = User.builder()
                .id(1L)
                .build();
        Role refRole = Role.builder()
                .id(2L)
                .build();
        Project refProject = Project.builder()
                .id(3L)
                .build();

        ProjectRole referenceProjectRole = new ProjectRole();
        referenceProjectRole.setId(1L);
        referenceProjectRole.setProject(refProject);
        referenceProjectRole.setRole(refRole);
        referenceProjectRole.setUser(refUser);
        referenceProjectRole.setRoleLevel("roleLevel");
        referenceProjectRole.setStartDate(LocalDate.EPOCH);
        referenceProjectRole.setEndDate(LocalDate.MAX);

        ProjectRoleDto projectRoleDto = ProjectRoleDto.builder()
                .id(1L)
                .user(UserDto.builder().id(1L).build())
                .role(RoleDto.builder().id(2L).build())
                .project(ProjectDto.builder().id(3L).build())
                .startDate(LocalDate.EPOCH)
                .endDate(LocalDate.MAX)
                .roleLevel("roleLevel")
                .build();

        ProjectRole projectRole = mapper.convertToEntity(projectRoleDto);
        assertEquals(referenceProjectRole, projectRole);
    }

    @Test
    @DisplayName("Конвертируем Entity ProjectRole в ДТО")
    void convertToDtoTest_OK() {
        ProjectRoleDto referenceProjectRoleDto = ProjectRoleDto.builder()
                .id(1L)
                .user(UserDto.builder().id(3L).build())
                .role(RoleDto.builder().id(2L).build())
                .project(ProjectDto.builder().id(1L).build())
                .startDate(LocalDate.EPOCH)
                .endDate(LocalDate.MAX)
                .roleLevel("roleLevel")
                .build();

        ProjectRole projectRole = new ProjectRole();
        projectRole.setId(1L);
        projectRole.setProject(Project.builder().id(1L).build());
        projectRole.setRole(Role.builder().id(2L).build());
        projectRole.setUser(User.builder().id(3L).build());
        projectRole.setRoleLevel("roleLevel");
        projectRole.setStartDate(LocalDate.EPOCH);
        projectRole.setEndDate(LocalDate.MAX);

        ProjectRoleDto projectRoleDto = mapper.convertToDto(projectRole);
        assertEquals(referenceProjectRoleDto, projectRoleDto);

    }
}