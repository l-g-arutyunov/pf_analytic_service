package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper mapper;

    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public UserDto convertToDto(User userEntity) {
        return mapper.map(userEntity, UserDto.class);
    }
}
