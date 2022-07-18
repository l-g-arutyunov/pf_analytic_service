package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectController {

    private final ProjectService service;

    @PutMapping("project")
    Long addProject(@RequestBody ProjectDto projectDto) {
        return service.addProject(projectDto);
    }

    @GetMapping("project/{id}")
    ProjectDto getProject(@PathVariable("id") Long id) {
        return service.getProject(id);
    }

    @GetMapping("project")
    List<ProjectDto> getAllProject() {
        return service.getAllProjects();
    }

    @DeleteMapping("project/{id}")
    Boolean deleteProjectById(@PathVariable("id") Long id) {
        return service.deleteProjectById(id);
    }

}
