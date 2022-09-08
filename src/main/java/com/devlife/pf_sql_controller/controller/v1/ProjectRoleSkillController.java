package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectRoleSkillDto;
import com.devlife.pf_sql_controller.service.ProjectRoleSkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectRoleSkillController {

    private final ProjectRoleSkillService service;

    @PostMapping("addProjectRoleSkill")
    void addProjectRoleSkill(@RequestBody ProjectRoleSkillDto projectRoleSkillDto) {
        service.addProjectRoleSkill(projectRoleSkillDto);
    }
    @GetMapping("projectRoleSkill/{id}")
    ProjectRoleSkillDto getProjectRoleSkill(@PathVariable("id") Long id) {
        return service.getProjectRoleSkill(id);
    }

    @GetMapping("projectRoleSkill")
    List<ProjectRoleSkillDto> getAllProjectRoleSkills() {
        return service.getAllProjectRoleSkills();
    }

    @DeleteMapping("projectRoleSkill/{id}")
    Boolean deleteProjectRoleSkillById(@PathVariable("id") Long id) {
        return service.deleteProjectRoleSkillById(id);
    }


}
