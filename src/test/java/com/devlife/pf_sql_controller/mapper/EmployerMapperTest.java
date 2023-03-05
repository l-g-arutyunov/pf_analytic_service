package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.UserGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("Employer mapper tests")
class EmployerMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    EmployerMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО Employer в Entity")
    void convertToEntityTest_OK() {
        Employer referenceEmployerEntity = new Employer();
        referenceEmployerEntity.setId(1L);
        referenceEmployerEntity.setName("Test");
        referenceEmployerEntity.setUserGroup(UserGroup.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build());
        EmployerDto employerDto = EmployerDto.builder()
                .id(1L)
                .name("Test")
                .userGroup(UserGroupDto.builder()
                        .id(1L)
                        .name("name")
                        .description("description")
                        .build())
                .build();
        Employer employer = mapper.convertToEntity(employerDto);
        assertEquals(referenceEmployerEntity, employer);
    }

    @Test
    @DisplayName("Конвертируем Entity Employer в ДТО")
    void convertToDtoTest_OK() {
        EmployerDto referenceEmployerDto = EmployerDto.builder()
                .id(1L)
                .name("Test")
                .userGroup(UserGroupDto.builder()
                        .id(1L)
                        .name("name")
                        .description("description")
                        .build())
                .build();
        Employer employerEntity = new Employer();
        employerEntity.setId(1L);
        employerEntity.setName("Test");
        employerEntity.setUserGroup(UserGroup.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build());
        EmployerDto employerDto = mapper.convertToDto(employerEntity);
        assertEquals(referenceEmployerDto,employerDto);
    }
}