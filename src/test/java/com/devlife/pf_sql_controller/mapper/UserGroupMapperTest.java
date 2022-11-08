package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.UserGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("User Group Mapper Tests")
class UserGroupMapperTest {
    @Autowired
    UserGroupMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО UserGroup в Entity")
    void convertToEntityTest_OK() {
        UserGroup referenceUserGroup = new UserGroup();
        referenceUserGroup.setId(1L);
        referenceUserGroup.setName("name");
        referenceUserGroup.setDescription("description");
        referenceUserGroup.setRoles(null);
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
        userGroup.setRoles(null);

        UserGroupDto userGroupDto = mapper.convertToDto(userGroup);
        assertEquals(referenceUserGroupDto,userGroupDto);
    }
}