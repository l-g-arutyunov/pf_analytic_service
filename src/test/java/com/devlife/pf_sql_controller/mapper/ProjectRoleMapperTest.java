package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("ProjectRole Mapper Tests")
class ProjectRoleMapperTest {

    @Autowired
    ProjectRoleMapper mapper;

    // TODO: 19.10.2022
    @Test
    void convertToEntity() {
        ProjectRole referenceProjectRole = new ProjectRole();
        referenceProjectRole.setId(1L);
        referenceProjectRole.setProject(null);
        referenceProjectRole.setRole(null);
        referenceProjectRole.setUser(null);
        referenceProjectRole.setRoleLevel("roleLevel");
        referenceProjectRole.setStartDate(null);
        referenceProjectRole.setEndDate(null);

        ProjectRoleDto projectRoleDto = ProjectRoleDto.builder().id(1L).user(null).role(null).
        project(null).startDate(null).endDate(null).roleLevel("roleLevel").build();

        ProjectRole projectRole= mapper.convertToEntity(projectRoleDto);
        assertEquals(referenceProjectRole,projectRole);
    }

    @Test
    void convertToDto() {
        ProjectRoleDto referenceProjectRoleDto = ProjectRoleDto.builder().id(1L).startDate(LocalDate.EPOCH)
                .endDate(LocalDate.EPOCH).roleLevel("roleLevel").build();

        ProjectRole projectRole = new ProjectRole();
        projectRole.setId(1L);
        projectRole.setRoleLevel("roleLevel");
        projectRole.setStartDate(LocalDate.EPOCH);
        projectRole.setEndDate(LocalDate.EPOCH);

        ProjectRoleDto projectRoleDto = mapper.convertToDto(projectRole);
        assertEquals(referenceProjectRoleDto,projectRoleDto);

    }
}