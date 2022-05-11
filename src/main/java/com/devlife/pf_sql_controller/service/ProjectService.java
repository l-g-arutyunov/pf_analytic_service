package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.mapper.ProjectMapper;
import com.devlife.pf_sql_controller.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper mapper;

    public Long addProject(ProjectDto project) {
        Project saveProject = projectRepository.save(mapper.convertToEntity(project));
        if (saveProject != null) {
            return saveProject.getId();
        }
        return null;
    }

    public ProjectDto getProject(Long id) {
        Project project = projectRepository.getById(id);
        return mapper.convertToDto(project);
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectsList = projectRepository.findAll();
        return projectsList.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Boolean deleteProjectById(Long id) {
        projectRepository.deleteById(id);
        return !projectRepository.existsById(id);
    }

}
