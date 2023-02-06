package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;

import com.devlife.pf_sql_controller.service.ProjectRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/projectRole")
public class ProjectRoleController {
    private final ProjectRoleService service;

    @PutMapping
    @PreAuthorize("hasAuthority('ROOT')")
    @Operation(summary = "Add or update project role", tags = {"projectRole"})
    ResponseEntity<ProjectRoleDto> addProjectRole(@RequestBody ProjectRoleDto projectRoleDto) {
        return ResponseEntity.ok(service.addProjectRole(projectRoleDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project role by id", tags = {"projectRole"})
    ResponseEntity<ProjectRoleDto> getProjectRole(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProjectRole(id));
    }

    @GetMapping
    @Operation(summary = "Get all project role by userGroupId", tags = {"projectRole"})
    ResponseEntity<List<ProjectRoleDto>> getAllProjectRole(
            @Parameter(description = "project id", required = true, name = "projectId")
            @RequestParam(name = "projectId") Long projectId) {
        return ResponseEntity.ok(service.getProjectRolesByProjectId(projectId));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOT')")
    @Operation(summary = "Delete project role by id", tags = {"projectRole"})
    HttpStatus deleteProjectRoleById(@PathVariable("id") Long id) {
        service.deleteProjectRoleById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/{id}/members")
    @Operation(summary = "Get all project members by project id", tags = {"projectRole"})
    ResponseEntity<Set<ProjectRoleDto>> getProjectMembersByProjectId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProjectMembersByProjectId(id));
    }

}
