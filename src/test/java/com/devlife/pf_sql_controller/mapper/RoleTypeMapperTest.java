package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.entity.RoleType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("RoleType Mapper Tests")
class RoleTypeMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    RoleTypeMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО RoleType в Entity")
    void convertToEntityTest_OK() {
        RoleType referenceRoleType = new RoleType();
        referenceRoleType.setId(1L);
        referenceRoleType.setName("name");
        referenceRoleType.setDescription("description");
        RoleTypeDto roleTypeDto = RoleTypeDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
        RoleType roleType = mapper.convertToEntity(roleTypeDto);
        assertEquals(referenceRoleType,roleType);
    }

    @Test
    @DisplayName("Конвертируем Entity RoleType в ДТО")
    void convertToDtoTest_OK() {
        RoleTypeDto referenceRoleTypeDto = RoleTypeDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
        RoleType roleType = new RoleType();
        roleType.setId(1L);
        roleType.setName("name");
        roleType.setDescription("description");
        RoleTypeDto roleTypeDto = mapper.convertToDto(roleType);
        assertEquals(referenceRoleTypeDto,roleTypeDto);
    }
}