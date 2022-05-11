package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Role;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.entity.UserGroupUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void convertToEntity() {
    }

    @Test
    void convertToDto() {
        UserDto referenceUserDto = UserDto.builder()
                .id(1L)
                .name("Test")
                .userGroups(Set.of(UserGroupDto.builder()
                        .id(1L)
                        .name("name")
                        .roles(null)
                        .description("description")
                        .build()))
                .build();

        User userEntity = new User();
        userEntity.setId(1L);
        userEntity.setName("Test");
        userEntity.setUserGroups(Set.of(UserGroup.builder()
                        .id(1L)
                        .name("name")
                        .roles(null)
                        .description("description")
                        .build()));
        UserDto userDto = mapper.convertToDto(userEntity);
        userDto.getUserGroups().forEach(x -> System.out.println(x.getClass()));

        assertEquals(referenceUserDto, userDto);
    }
}