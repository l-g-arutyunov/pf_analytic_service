package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.entity.Role;
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
@DisplayName("Role Mapper Tests")
class RoleMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    RoleMapper mapper;

    @Test
    @DisplayName("Конвертируем Дто Role в Энтити")
    void convertToEntityTest_TestOK() {
        Role referenceRole = new Role();
        referenceRole.setId(1L);
        referenceRole.setName("name");
        referenceRole.setDescription("description");
        referenceRole.setUserGroupId(1L);
        referenceRole.setRoleType(RoleType.builder()
                .id(1L)
                .build());

        RoleDto roleDto = RoleDto.builder().id(1L).name("name")
                .description("description")
                .userGroupId(1L)
                .roleType(RoleTypeDto.builder()
                        .id(1L)
                        .build())
                .build();
        Role role = mapper.convertToEntity(roleDto);
        assertEquals(referenceRole,role);
    }

    @Test
    @DisplayName("Конвертируем Энтити Role в ДТО")
    void convertToDtoTest_OK() {
        RoleDto referenceRoleDto = RoleDto.builder().id(1L).name("name")
                .description("description")
                .userGroupId(1L)
                .roleType(RoleTypeDto.builder()
                        .id(1L)
                        .build())
                .build();
        Role role = new Role();
        role.setId(1L);
        role.setName("name");
        role.setDescription("description");
        role.setUserGroupId(1L);
        role.setRoleType(RoleType.builder()
                .id(1L)
                .build());
        RoleDto roleDto= mapper.convertToDto(role);
        assertEquals(referenceRoleDto,roleDto);
    }
}