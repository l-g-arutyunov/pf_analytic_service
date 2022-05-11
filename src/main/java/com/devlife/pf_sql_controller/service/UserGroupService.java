package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.mapper.UserGroupMapper;
import com.devlife.pf_sql_controller.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;
    private final UserGroupMapper mapper;

    public Long addUserGroup(UserGroup userGroup) {
        UserGroup saveUserGroup = userGroupRepository.save(userGroup);
        if (saveUserGroup != null) {
            return saveUserGroup.getId();
        }
        return null;
    }

    public UserGroup getUserGroup(Long id) {
        UserGroup userGroup = userGroupRepository.getById(id);
        return userGroup;
    }

    public List<UserGroupDto> getAllUserGroups() {
        List<UserGroup> userGroupsList = userGroupRepository.findAll();
        return userGroupsList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteUserGroupById(Long id) {
        userGroupRepository.deleteById(id);
        return !userGroupRepository.existsById(id);
    }

}
