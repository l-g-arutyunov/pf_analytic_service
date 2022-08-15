package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class RoleController {

    private final RoleService service;

    @PutMapping("role")
    Long addRole(@RequestBody RoleDto roleDto) {
        return service.addRole((roleDto));
    }

    @GetMapping("role/{id}")
    RoleDto getRole(@PathVariable("id") Long id) {
        return service.getRole(id);
    }

    @GetMapping("role")
    List<RoleDto> getAllRoles() {
        return service.getAllRoles();
    }

    @DeleteMapping("role/{id}")
    Boolean deleteRoleById(@PathVariable("id") Long id) {
        return service.deleteRoleById(id);
    }

}
