package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.UserGroupUserDto;
import com.devlife.pf_sql_controller.entity.UserGroupUser;
import com.devlife.pf_sql_controller.mapper.UserGroupUserMapper;
import com.devlife.pf_sql_controller.repository.UserGroupUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupUserService {
    private final UserGroupUserRepository userGroupUserRepository;
    private final UserGroupUserMapper mapper;

    public void addUserGroupUser(UserGroupUserDto userGroupUser) {
        userGroupUserRepository.save(mapper.convertToEntity(userGroupUser));
    }

    public UserGroupUserDto getUserGroupUser(Long id) {
        UserGroupUser userGroupUser = userGroupUserRepository.getById(id);
        return mapper.convertToDto(userGroupUser);
    }

    public List<UserGroupUserDto> getAllUsersInGroups() {
        List<UserGroupUser> userGroupUsersList = userGroupUserRepository.findAll();
        return userGroupUsersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteUserGroupUserById(Long id) {
        userGroupUserRepository.deleteById(id);
        return !userGroupUserRepository.existsById(id);
    }

}
