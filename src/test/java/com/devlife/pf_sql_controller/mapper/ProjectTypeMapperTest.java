package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.entity.ProjectType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("ProjectType Mapper Tests")
class ProjectTypeMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
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