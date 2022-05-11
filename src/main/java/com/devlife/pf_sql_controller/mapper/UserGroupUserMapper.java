package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserGroupUserDto;
import com.devlife.pf_sql_controller.entity.UserGroupUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class UserGroupUserMapper {
    private final ModelMapper mapper;

    public UserGroupUserMapper(ModelMapper mapper) {
        this.mapper = mapper;
        mapper.addMappings(new PropertyMap<UserGroupUserDto, UserGroupUser>() {
            @Override
            protected void configure() {
                map().getUserGroupUserId().setUserGroupId(source.getGroupId());
                map().getUserGroupUserId().setUserId(source.getUserId());
            }
        });
    }

    public UserGroupUser convertToEntity(UserGroupUserDto userGroupUserDto) {
        return mapper.map(userGroupUserDto, UserGroupUser.class);
    }

    public UserGroupUserDto convertToDto(UserGroupUser userGroupUserEntity) {
        return mapper.map(userGroupUserEntity, UserGroupUserDto.class);
    }
}
