package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserGroupUserDto;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.entity.UserGroupUser;
import com.devlife.pf_sql_controller.entity.embeddable.UserGroupUserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("UserGroupUser Mapper Tests")
class UserGroupUserMapperTest {
    @Autowired
    UserGroupUserMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО UserGroupUser в Entity")
    void convertToEntityTest_OK() {
        UserGroupUser referenceUserGroupUser = new UserGroupUser();
        referenceUserGroupUser.setUserGroupUserId(null);
        referenceUserGroupUser.setIsOwner(true);
        referenceUserGroupUser.setDate(LocalDate.EPOCH);
        referenceUserGroupUser.setIsActive(true);
        UserGroupUserDto userGroupUserDto = UserGroupUserDto.builder()
                .isActive(true).isOwner(true)
                .date(LocalDate.EPOCH).groupId(null).userId(null).build();
        UserGroupUser userGroupUser = mapper.convertToEntity(userGroupUserDto);
        assertEquals(referenceUserGroupUser,userGroupUser);
    }

    @Test
    @DisplayName("Конвертируем Entity UserGroupUser в ДТО")
    void convertToDtoTest_OK() {
        UserGroupUserDto referenceUserGroupUserDto = UserGroupUserDto.builder()
                .isActive(true).isOwner(true)
                .date(LocalDate.EPOCH).groupId(null).userId(null).build();
        UserGroupUser userGroupUser = new UserGroupUser();
        userGroupUser.setUserGroupUserId(null);
        userGroupUser.setIsOwner(true);
        userGroupUser.setDate(LocalDate.EPOCH);
        userGroupUser.setIsActive(true);

        UserGroupUserDto userGroupUserDto = mapper.convertToDto(userGroupUser);
        assertEquals(referenceUserGroupUserDto,userGroupUserDto);
    }
}