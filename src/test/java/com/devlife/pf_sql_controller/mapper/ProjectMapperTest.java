package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.entity.UserGroup;
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
@DisplayName("Project Mapper Tests")
class ProjectMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
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
        referenceProject.setEmployer(Employer.builder().id(1L).build());
        referenceProject.setProjectType(ProjectType.builder().id(1L).build());
        referenceProject.setUserGroup(UserGroup.builder().id(1L).build());
        ProjectDto projectDto = ProjectDto.builder().id(1L).name("name").description("description")
                .startDate(LocalDate.EPOCH)
                .endDate(LocalDate.EPOCH)
                .employer(EmployerDto.builder().id(1L).build())
                .projectType(ProjectTypeDto.builder().id(1L).build())
                .userGroup(UserGroupDto.builder().id(1L).build())
                .build();
        Project project = mapper.convertToEntity(projectDto);
        assertEquals(referenceProject, project);
    }

    @Test
    @DisplayName("Конвертируем Энтити в ДТО")
    void convertToDtoTest_OK() {
        ProjectDto referenceProjectDto = ProjectDto.builder().id(1L).name("name").description("description")
                .startDate(LocalDate.EPOCH).endDate(LocalDate.EPOCH)
                .employer(EmployerDto.builder().id(1L).build())
                .projectType(ProjectTypeDto.builder().id(1L).build())
                .userGroup(UserGroupDto.builder().id(1L).build())
                .build();
        Project projectEntity = new Project(1L,"name","description",LocalDate.EPOCH,LocalDate.EPOCH,
                Employer.builder().id(1L).build(),ProjectType.builder().id(1L).build(),UserGroup.builder().id(1L).build(), null);
        ProjectDto projectDto = mapper.convertToDto(projectEntity);
        assertEquals(referenceProjectDto, projectDto);
    }
}