package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectTypeDto;
import com.devlife.pf_sql_controller.entity.ProjectType;
import com.devlife.pf_sql_controller.exception.ProjectTypeNotFoundException;
import com.devlife.pf_sql_controller.mapper.ProjectTypeMapper;
import com.devlife.pf_sql_controller.repository.ProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProjectTypeService {
    private final ProjectTypeRepository projectTypeRepository;
    private final ProjectTypeMapper mapper;

    public ProjectTypeDto addProjectType(ProjectTypeDto projectType) {
        ProjectType saveProjectType = projectTypeRepository.save(mapper.convertToEntity(projectType));
        return mapper.convertToDto(saveProjectType);
    }

    public ProjectTypeDto getProjectType(Long id) {
        final Optional<ProjectType> projectTypeOpt = projectTypeRepository.findById(id);
        final ProjectType projectType = projectTypeOpt.orElseThrow(() -> new ProjectTypeNotFoundException("id : " + id));
        return mapper.convertToDto(projectType);
    }

    public List<ProjectTypeDto> getAllProjectTypes() {
        final List<ProjectType> projectTypesList = projectTypeRepository.findAll();
        return projectTypesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteProjectTypeById(Long id) {
        final Optional<ProjectType> projectTypeOpt = projectTypeRepository.findById(id);
        final ProjectType projectType = projectTypeOpt.orElseThrow(() -> new ProjectTypeNotFoundException("id : " + id));
        projectTypeRepository.delete(projectType);
    }
}
