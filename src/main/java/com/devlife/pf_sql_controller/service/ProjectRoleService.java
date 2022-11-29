package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.ProjectRole;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.exception.BusinessLogicException;
import com.devlife.pf_sql_controller.exception.ProjectRoleNotFoundException;
import com.devlife.pf_sql_controller.mapper.ProjectRoleMapper;
import com.devlife.pf_sql_controller.repository.ProjectRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectRoleService {
    private final ProjectRoleRepository projectRoleRepository;
    private final ProjectRoleMapper mapper;

    public ProjectRoleDto addProjectRole(ProjectRoleDto projectRole) {
        ProjectRole saveProjectRole = projectRoleRepository.save(mapper.convertToEntity(projectRole));
        return mapper.convertToDto(saveProjectRole);
    }

    public ProjectRoleDto getProjectRole(Long id) {
        Optional<ProjectRole> projectRoleOpt = projectRoleRepository.findById(id);
        ProjectRole projectRole = projectRoleOpt.orElseThrow(() -> new ProjectRoleNotFoundException("id : " + id));
        return mapper.convertToDto(projectRole);
    }

    public List<ProjectRoleDto> getProjectRolesByProjectId(Long projectId) {
        List<ProjectRole> projectRolesList = projectRoleRepository.getByProject(Project.builder().id(projectId).build());
        return projectRolesList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public void deleteProjectRoleById(Long id) {
        final Optional<ProjectRole> projectTypeOpt = projectRoleRepository.findById(id);
        final ProjectRole projectType = projectTypeOpt.orElseThrow(() -> new ProjectRoleNotFoundException("id : " + id));
        projectRoleRepository.delete(projectType);
    }

    public Set<ProjectRoleDto> addUserToProject(Project project, Map<User, Set<AddProjectMemberReq>> data) {
        Set<ProjectRole> projectRoles = mapper.convertAddProjectMemberReqToEntity(project, data);

        Set<ProjectRole> existsProjectRole = projectRoles.stream()
                .flatMap(i -> projectRoleRepository.userAndRoleAlreadyExistInProject(i.getProject().getId(),
                        i.getRole().getId(), i.getUser().getId()).stream())
                .collect(Collectors.toSet());
        if (!existsProjectRole.isEmpty()) {
            throw new BusinessLogicException("ProjectRole already exists: " +  existsProjectRole);
        }
        projectRoleRepository.saveAll(projectRoles);
        return projectRoles.stream().map(mapper::convertToDto).collect(Collectors.toSet());
    }
}
