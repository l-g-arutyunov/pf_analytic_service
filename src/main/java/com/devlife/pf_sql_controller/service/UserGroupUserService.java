package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.entity.UserGroupUser;
import com.devlife.pf_sql_controller.repository.UserGroupUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupUserService {
    private final UserGroupUserRepository userGroupUserRepository;

    public void addUserGroupUser(UserGroupUser userGroupUser) {
        userGroupUserRepository.save(userGroupUser);
    }

    public UserGroupUser getUserGroupUser(Long id) {
        UserGroupUser userGroupUser = userGroupUserRepository.getById(id);
        return userGroupUser;
    }

    public List<UserGroupUser> getAllUsersInGroups() {
        List<UserGroupUser> userGroupUsersList = userGroupUserRepository.findAll();
        return userGroupUsersList;
    }

    public Boolean deleteUserGroupUserById(Long id) {
        userGroupUserRepository.deleteById(id);
        return !userGroupUserRepository.existsById(id);
    }

}
