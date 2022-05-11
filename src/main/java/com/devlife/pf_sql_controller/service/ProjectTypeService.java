package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.repository.ProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypeRepository;

    public Long addProjectType(ProjectType projectType) {
        ProjectType saveProjectType = projectTypeRepository.save(projectType);
        if (saveProjectType != null) {
            return saveProjectType.getId();
        }
        return null;
    }

    public ProjectType getProjectType(Long id) {
        ProjectType projectType = projectTypeRepository.getById(id);
        return projectType;
    }

    public List<ProjectType> getAllProjectTypes() {
        List<ProjectType> projectTypesList = projectTypeRepository.findAll();
        return projectTypesList;
    }

    public Boolean deleteProjectTypeById(Long id) {
        projectTypeRepository.deleteById(id);
        return !projectTypeRepository.existsById(id);
    }

}
