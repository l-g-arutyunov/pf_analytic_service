package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.mapper.ProjectTypeMapper;
import com.devlife.pf_sql_controller.repository.ProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectTypeMapper mapper;

    public Long addProjectType(ProjectTypeDto projectType) {
        ProjectType saveProjectType = projectTypeRepository.save(mapper.convertToEntity(projectType));
        if (saveProjectType != null) {
            return saveProjectType.getId();
        }
        return null;
    }

    public ProjectTypeDto getProjectType(Long id) {
        ProjectType projectType = projectTypeRepository.getById(id);
        return mapper.convertToDto(projectType);
    }

    public List<ProjectTypeDto> getAllProjectTypes() {
        List<ProjectType> projectTypesList = projectTypeRepository.findAll();
        return projectTypesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteProjectTypeById(Long id) {
        projectTypeRepository.deleteById(id);
        return !projectTypeRepository.existsById(id);
    }

}
