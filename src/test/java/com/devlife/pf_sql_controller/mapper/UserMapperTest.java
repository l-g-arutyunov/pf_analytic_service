package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("User mapper tests")
class UserMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    UserMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО User в Entity")
    void convertToEntityTest_OK() {
        User referenceUserEntity = new User();
        referenceUserEntity.setId(1L);
        referenceUserEntity.setName("Test");
        referenceUserEntity.setUserGroups(Set.of(UserGroup.builder()
                .id(1L)
                .name("name")
                .roles(null)
                .description("description")
                .build()));
        UserDto userDto = UserDto.builder()
                .id(1L)
                .name("Test")
                .userGroups(Set.of(UserGroupDto.builder()
                        .id(1L)
                        .name("name")
                        .roles(null)
                        .description("description")
                        .build()))
                .build();
        User user = mapper.convertToEntity(userDto);
        assertEquals(referenceUserEntity,user);
    }

    @Test
    @DisplayName("Конвертируем Entity User в ДТО")
    void convertToDtoTest_OK() {
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