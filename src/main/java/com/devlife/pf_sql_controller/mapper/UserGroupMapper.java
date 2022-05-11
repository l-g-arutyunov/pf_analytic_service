package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.UserGroup;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserGroupMapper {
    private final ModelMapper mapper;

    public UserGroup convertToEntity(UserGroupDto userGroupDto) {
        return mapper.map(userGroupDto, UserGroup.class);
    }

    public UserGroupDto convertToDto(UserGroup userGroupEntity) {
        return mapper.map(userGroupEntity, UserGroupDto.class);
    }
}
