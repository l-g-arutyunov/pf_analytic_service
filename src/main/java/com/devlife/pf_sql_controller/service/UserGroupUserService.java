package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.UserGroupUserDto;
import com.devlife.pf_sql_controller.entity.UserGroupUser;
import com.devlife.pf_sql_controller.entity.embeddable.UserGroupUserId;
import com.devlife.pf_sql_controller.mapper.UserGroupUserMapper;
import com.devlife.pf_sql_controller.repository.UserGroupUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Transactional
    public void addUserToUserGroup(Long userId, Long userGroupId, Boolean isOwner, LocalDate changeDate) {
        //TODO В группе может быть только 1 владелец группы? Возможно добавить проверку что он уже задан.
        UserGroupUser relationship = new UserGroupUser();
        relationship.setUserGroupUserId(
                UserGroupUserId.builder()
                        .userGroupId(userGroupId)
                        .userId(userId)
                        .build()
                );
        relationship.setIsActive(true);
        relationship.setDate(changeDate == null ? LocalDate.now() : changeDate);
        relationship.setIsOwner(isOwner);
        userGroupUserRepository.saveAndFlush(relationship);
    }

    public Boolean userExistInUserGroup(Long userId, Long userGroupId) {
        return userGroupUserRepository.checkUserExistInUserGroup(userId, userGroupId);
    }

}
