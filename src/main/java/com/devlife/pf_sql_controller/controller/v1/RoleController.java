package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.RoleDto;
import com.devlife.pf_sql_controller.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/role")
public class RoleController {

    private final RoleService service;

    @PutMapping
    @Operation(summary = "Add or update role", tags = {"role"})
    ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto) {
        return  ResponseEntity.ok(service.addRole(roleDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role by id", tags = {"role"})
    ResponseEntity<RoleDto> getRole(@PathVariable("id") Long id) {
        return  ResponseEntity.ok(service.getRole(id));
    }

    @GetMapping
    @Operation(summary = "Get all role by userGroupId", tags = {"role"})
    ResponseEntity<List<RoleDto>> getAllRoles(
            @Parameter(description = "user group id", required = true, name = "userGroupId")
            @RequestParam(name = "userGroupId") Long userGroupId) {
        return  ResponseEntity.ok(service.getRolesByUserGroupId(userGroupId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role by id", tags = {"role"})
    HttpStatus deleteRoleById(@PathVariable("id") Long id) {
        service.deleteRoleById(id);
        return HttpStatus.OK;
    }

}
