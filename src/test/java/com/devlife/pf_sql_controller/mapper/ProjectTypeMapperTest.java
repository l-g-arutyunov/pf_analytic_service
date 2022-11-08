package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("ProjectType Mapper Tests")
class ProjectTypeMapperTest {
    @Autowired
    ProjectTypeMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО ProjectType в Entity")
    void convertToEntityTest_OK() {
        ProjectType referenceProjectType = ProjectType.builder()
                .id(1L)
                .name("name")
                .build();
        ProjectTypeDto projectTypeDto = ProjectTypeDto.builder()
                .id(1L)
                .name("name")
                .build();
        ProjectType projectType= mapper.convertToEntity(projectTypeDto);
        assertEquals(referenceProjectType,projectType);
    }

    @Test
    @DisplayName("Конвертируем Entity ProjectType в ДТО")
    void convertToDtoTest_OK() {
        ProjectTypeDto referenceProjectTypeDto = ProjectTypeDto.builder()
                .id(1L)
                .name("name")
                .build();
        ProjectType projectType = ProjectType.builder()
                .id(1L)
                .name("name")
                .build();
        ProjectTypeDto projectTypeDto = mapper.convertToDto(projectType);
        assertEquals(referenceProjectTypeDto,projectTypeDto);
    }
}