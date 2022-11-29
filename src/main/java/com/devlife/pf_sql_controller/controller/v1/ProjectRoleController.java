package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;

import com.devlife.pf_sql_controller.service.ProjectRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/projectRole")
public class ProjectRoleController {
    private final ProjectRoleService service;

    @PutMapping
    @Operation(summary = "Add or update project role", tags = {"projectRole"})
    ResponseEntity<ProjectRoleDto> addProjectRole(@RequestBody ProjectRoleDto projectRoleDto) {
        return ResponseEntity.ok(service.addProjectRole(projectRoleDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project role by id", tags = {"projectRole"})
    ResponseEntity<ProjectRoleDto> getProjectType(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProjectRole(id));
    }

    @GetMapping
    @Operation(summary = "Get all project role by userGroupId", tags = {"projectRole"})
    ResponseEntity<List<ProjectRoleDto>> getAllProjectType(
            @Parameter(description = "user group id", required = true, name = "userGroupId")
            @RequestParam(name = "userGroupId") Long userGroupId) {
        return ResponseEntity.ok(service.getProjectRolesByProjectId(userGroupId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project role by id", tags = {"projectRole"})
    HttpStatus deleteProjectTypeById(@PathVariable("id") Long id) {
        service.deleteProjectRoleById(id);
        return HttpStatus.OK;
    }

}
