package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.UserGroupUserDto;
import com.devlife.pf_sql_controller.mapper.UserGroupUserMapper;
import com.devlife.pf_sql_controller.service.UserGroupUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class UserGroupUserController {

    private final UserGroupUserService service;
    private final UserGroupUserMapper mapper;

    @PostMapping("addUserToGroup")
    void addUserGroupUser(@RequestBody UserGroupUserDto userGroupUserDto) {
        service.addUserGroupUser(mapper.convertToEntity(userGroupUserDto));
    }

    @GetMapping("userGroupUser/{id}")
    UserGroupUserDto getUserGroupUser(@PathVariable("id") Long id) {
        return mapper.convertToDto(service.getUserGroupUser(id));
    }

    @GetMapping("userGroupUser")
    List<UserGroupUserDto> getAllUserGroupUsers() {
        return service.getAllUsersInGroups().stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("userGroupUser/{id}")
    Boolean deleteUserGroupUserById(@PathVariable("id") Long id) {
        return service.deleteUserGroupUserById(id);
    }

}
