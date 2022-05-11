package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.repository.ProjectRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;

    public Long addProjectRole(ProjectRole projectRole) {
        ProjectRole saveProjectRole = projectRoleRepository.save(projectRole);
        if (saveProjectRole != null) {
            return saveProjectRole.getId();
        }
        return null;
    }

    public ProjectRole getProjectRole(Long id) {
        ProjectRole projectRole = projectRoleRepository.getById(id);
        return projectRole;
    }

    public List<ProjectRole> getAllProjectRoles() {
        List<ProjectRole> projectRolesList = projectRoleRepository.findAll();
        return projectRolesList;
    }

    public Boolean deleteProjectRoleById(Long id) {
        projectRoleRepository.deleteById(id);
        return !projectRoleRepository.existsById(id);
    }

}
