package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.mapper.ProjectTypeMapper;
import com.devlife.pf_sql_controller.service.ProjectTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectTypeController {

    private final ProjectTypeService service;


    @PutMapping("projectType")
    Long addProjectType(@RequestBody ProjectTypeDto projectTypeDto) {
        return service.addProjectType((projectTypeDto));
    }

    @GetMapping("projectType/{id}")
    ProjectTypeDto getProjectType(@PathVariable("id") Long id) {
        return service.getProjectType(id);
    }

    @GetMapping("projectType")
    List<ProjectTypeDto> getAllProjectType() {
        return service.getAllProjectTypes();
    }

    @DeleteMapping("projectType/{id}")
    Boolean deleteProjectTypeById(@PathVariable("id") Long id) {
        return service.deleteProjectTypeById(id);
    }

}
