package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class UserGroupController {

    private final UserGroupService service;

    @PutMapping("userGroup")
    Long addUserGroup(@RequestBody UserGroupDto userGroupDto) {
        return service.addUserGroup(userGroupDto);
    }

    @GetMapping("userGroup/{id}")
    UserGroupDto getUserGroup(@PathVariable("id") Long id) {
        return service.getUserGroup(id);
    }

    @GetMapping("userGroup")
    List<UserGroupDto> getAllUserGroups() {
        return service.getAllUserGroups();
    }

    @DeleteMapping("userGroup/{id}")
    Boolean deleteUserGroupById(@PathVariable("id") Long id) {
        return service.deleteUserGroupById(id);
    }

}
