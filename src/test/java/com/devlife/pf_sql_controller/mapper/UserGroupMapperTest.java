package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserGroupDto;
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
@DisplayName("User Group Mapper Tests")
class UserGroupMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    UserGroupMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО UserGroup в Entity")
    void convertToEntityTest_OK() {
        UserGroup referenceUserGroup = new UserGroup();
        referenceUserGroup.setId(1L);
        referenceUserGroup.setName("name");
        referenceUserGroup.setDescription("description");
        UserGroupDto userGroupDto = UserGroupDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .roles(null)
                .build();
        UserGroup userGroup = mapper.convertToEntity(userGroupDto);
        assertEquals(referenceUserGroup,userGroup);
    }

    @Test
    @DisplayName("Конвертируем Entity UserGroup в ДТО")
    void convertToDtoTest_OK() {
        UserGroupDto referenceUserGroupDto = UserGroupDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .build();
        UserGroup userGroup = new UserGroup();
        userGroup.setId(1L);
        userGroup.setName("name");
        userGroup.setDescription("description");

        UserGroupDto userGroupDto = mapper.convertToDto(userGroup);
        assertEquals(referenceUserGroupDto,userGroupDto);
    }
}