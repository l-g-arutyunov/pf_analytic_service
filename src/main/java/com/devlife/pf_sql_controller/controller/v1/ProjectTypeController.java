package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.service.ProjectTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/projectType")
public class ProjectTypeController {
    private final ProjectTypeService service;

    @PutMapping
    @Operation(summary = "Add or update project type", tags = {"projectType"})
    @PreAuthorize("hasAuthority('ROOT')")
    ResponseEntity<ProjectTypeDto> addProjectType(@RequestBody ProjectTypeDto projectTypeDto) {
        return ResponseEntity.ok(service.addProjectType((projectTypeDto)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get project type by id", tags = {"projectType"})
    ResponseEntity<ProjectTypeDto> getProjectType(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getProjectType(id));
    }

    @GetMapping
    @Operation(summary = "Get all project type", tags = {"projectType"})
    ResponseEntity<List<ProjectTypeDto>> getAllProjectType() {
        return ResponseEntity.ok(service.getAllProjectTypes());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete project type by id", tags = {"projectType"})
    @PreAuthorize("hasAuthority('ROOT')")
    HttpStatus deleteProjectTypeById(@PathVariable("id") Long id) {
        service.deleteProjectTypeById(id);
        return HttpStatus.OK;
    }

}
