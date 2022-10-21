package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.entity.UserGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("Project Mapper Tests")
class ProjectMapperTest {
    @Autowired
    ProjectMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО в Энтити")
    void convertToEntityTest_OK() {
        Project referenceProject = new Project();
        referenceProject.setId(1L);
        referenceProject.setName("name");
        referenceProject.setDescription("description");
        referenceProject.setStartDate(LocalDate.EPOCH);
        referenceProject.setEndDate(LocalDate.EPOCH);
        referenceProject.setEmployer(null);
//        projectEntity.setEmployer(Employer.builder().id(1L).name("name").userGroup(null).build());
        referenceProject.setProjectType(null);
//        projectEntity.setProjectType(ProjectType.builder().id(1L).name("name").build());
        referenceProject.setUserGroup(null);
//        projectEntity.setUserGroup(UserGroup.builder().id(1L).name("name")
//                .roles(null).description("description").build());

        ProjectDto projectDto = ProjectDto.builder().id(1L).name("name").description("description")
                .startDate(LocalDate.EPOCH).endDate(LocalDate.EPOCH).employer(null)
                .projectType(null).userGroup(null).build();

        Project project = mapper.convertToEntity(projectDto);
        assertEquals(referenceProject,project);
    }

    @Test
    @DisplayName("Конвертируем Энтити в ДТО")
    void convertToDtoTest_OK() {
        ProjectDto referenceProjectDto = ProjectDto.builder().id(1L).name("name").description("description")
                .startDate(LocalDate.EPOCH).endDate(LocalDate.EPOCH).employer(null)
                .projectType(null).userGroup(null).build();

        Project projectEntity = new Project(1L,"name","description",LocalDate.EPOCH,LocalDate.EPOCH,
                null,null,null);

        ProjectDto projectDto = mapper.convertToDto(projectEntity);
        assertEquals(referenceProjectDto,projectDto);
    }
}