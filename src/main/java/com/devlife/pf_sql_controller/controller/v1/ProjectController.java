package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectController {
    private final ProjectService service;

    @PutMapping("project/{externalUserId}")
    ResponseEntity<ProjectDto> addProject(
            @PathVariable Long externalUserId,
            @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(service.addProject(projectDto, externalUserId));
    }

    @GetMapping("project/{externalUserId}")
    ResponseEntity<Page<Set<ProjectDto>>> getProjectsByUser(
            @PathVariable Long externalUserId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getProjectsByUser(externalUserId, pageable));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("project/{id}")
    ProjectDto getProject(@PathVariable("id") Long id) {
        return service.getProject(id);
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("project")
    List<ProjectDto> getAllProject() {
        return service.getAllProjects();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("project/{id}")
    Boolean deleteProjectById(@PathVariable("id") Long id) {
        return service.deleteProjectById(id);
    }


}
