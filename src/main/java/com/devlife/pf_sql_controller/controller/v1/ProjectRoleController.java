package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.mapper.ProjectRoleMapper;
import com.devlife.pf_sql_controller.service.ProjectRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectRoleController {

    private final ProjectRoleService service;
    private final ProjectRoleMapper mapper;

    @PutMapping("projectRole")
    Long addProjectRole(@RequestBody ProjectRoleDto projectRoleDto) {
        return service.addProjectRole(mapper.convertToEntity(projectRoleDto));
    }

    @GetMapping("projectRole/{id}")
    ProjectRoleDto getProjectRole(@PathVariable("id") Long id) {
        return mapper.convertToDto(service.getProjectRole(id));
    }

    @GetMapping("projectRole")
    List<ProjectRoleDto> getAllProjectRoles() {
        return service.getAllProjectRoles().stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    @DeleteMapping("projectRole/{id}")
    Boolean deleteProjectRoleById(@PathVariable("id") Long id) {
        return service.deleteProjectRoleById(id);
    }

}
