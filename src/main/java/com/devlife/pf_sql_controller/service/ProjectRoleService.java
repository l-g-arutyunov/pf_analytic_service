package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.dto.asyncMessageModel.ProjectUserLinkAsyncModel;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.enums.EventType;
import com.devlife.pf_sql_controller.eventPublisher.ProjectUserLinkPublisher;
import com.devlife.pf_sql_controller.exception.BusinessLogicException;
import com.devlife.pf_sql_controller.exception.ProjectNotFoundException;
import com.devlife.pf_sql_controller.exception.ProjectRoleNotFoundException;
import com.devlife.pf_sql_controller.mapper.ProjectRoleMapper;
import com.devlife.pf_sql_controller.repository.ProjectRepository;
import com.devlife.pf_sql_controller.repository.ProjectRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRepository projectRepository;
    private final ProjectRoleMapper projectRoleMapper;
    private final ProjectUserLinkPublisher projectUserLinkPublisher;

    public ProjectRoleDto addProjectRole(ProjectRoleDto projectRole) {
        ProjectRole saveProjectRole = projectRoleRepository.save(projectRoleMapper.convertToEntity(projectRole));
        return projectRoleMapper.convertToDto(saveProjectRole);
    }

    public ProjectRoleDto getProjectRole(Long id) {
        Optional<ProjectRole> projectRoleOpt = projectRoleRepository.findById(id);
        ProjectRole projectRole = projectRoleOpt.orElseThrow(() -> new ProjectRoleNotFoundException("id : " + id));
        return projectRoleMapper.convertToDto(projectRole);
    }

    public List<ProjectRoleDto> getProjectRolesByProjectId(Long projectId) {
        Set<ProjectRole> projectRolesList = projectRoleRepository.getByProject(Project.builder().id(projectId).build());
        return projectRolesList.stream().map(projectRoleMapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteProjectRoleById(Long id) {
        final Optional<ProjectRole> projectTypeOpt = projectRoleRepository.findById(id);
        final ProjectRole projectType = projectTypeOpt.orElseThrow(() -> new ProjectRoleNotFoundException("id : " + id));
        projectRoleRepository.delete(projectType);
    }

    public Set<ProjectRoleDto> addUserToProject(Project project, Map<User, Set<AddProjectMemberReq>> data) {
        Set<ProjectRole> projectRoles = projectRoleMapper.convertAddProjectMemberReqToEntity(project, data);

        Set<ProjectRole> existsProjectRole = projectRoles.stream()
                .flatMap(i -> projectRoleRepository.userAndRoleAlreadyExistInProject(i.getProject().getId(),
                        i.getRole().getId(), i.getUser().getId()).stream())
                .collect(Collectors.toSet());
        if (!existsProjectRole.isEmpty()) {
            throw new BusinessLogicException("ProjectRole already exists: " +  existsProjectRole);
        }
        projectRoleRepository.saveAll(projectRoles);

        Set<ProjectRoleDto> projectRoleDtoSet = projectRoles.stream().map(projectRoleMapper::convertToDto).collect(Collectors.toSet());

        projectRoleDtoSet.forEach(pr -> projectUserLinkPublisher.sendMessage(
                ProjectUserLinkAsyncModel.builder().project(pr.getProject()).user(pr.getUser()).build(), EventType.CREATE)
        );
        return projectRoleDtoSet;
    }

    public Set<ProjectRoleDto> getProjectMembersByProjectId(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException(Objects.toString(id));
        }
        return projectRoleRepository.getByProject(Project.builder().id(id).build()).stream()
                .map(projectRoleMapper::convertToDto).collect(Collectors.toSet());
    }
}
