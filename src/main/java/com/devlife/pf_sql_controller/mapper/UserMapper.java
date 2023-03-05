package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.UserDto;
import com.devlife.pf_sql_controller.dto.asyncMessageModel.UserAsyncModel;
import com.devlife.pf_sql_controller.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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

    public User convertToEntity(UserAsyncModel userAsyncModel) {
        return mapper.map(userAsyncModel, User.class);
    }

    @PostConstruct
    public void setupMapper() {
        mapper.addMappings(new PropertyMap<UserAsyncModel, User>() {
                    @Override
                    protected void configure() {
                        map().setExternalId(source.getId());
                        map().setFirstName(source.getProfile().getFirstName());
                        map().setLastName(source.getProfile().getLastName());
                        map().setNickName(source.getProfile().getNickname());
                    }
                });
    }

}
