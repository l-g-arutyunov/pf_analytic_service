package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.exception.UserNotFoundException;
import com.devlife.pf_sql_controller.mapper.ProjectMapper;
import com.devlife.pf_sql_controller.repository.ProjectRepository;
import com.devlife.pf_sql_controller.repository.UserGroupRepository;
import com.devlife.pf_sql_controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    public static final String USER_GROUP_OF = "User group of %s1";
    public static final boolean USER_IS_OWNER = true;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final ProjectMapper mapper;
    private final UserGroupUserService userGroupUserService;

    @Transactional
    public ProjectDto addProject(ProjectDto projectDto, Long userExternalId) {
        User user = userRepository.getByExternalId(userExternalId).orElseThrow(UserNotFoundException::new);
        Project project = mapper.convertToEntity(projectDto);
        if (project.getUserGroup() != null && !userGroupUserService.userExistInUserGroup(user.getId(), project.getUserGroup().getId())) {
            throw new IllegalArgumentException("User not exist in the user group");
        }
        if (project.getUserGroup() == null) {
            UserGroup userGroup = userGroupRepository.save(
                    UserGroup.builder()
                            .name(String.format(USER_GROUP_OF, project.getName()))
                            .build()
            );
            userGroupUserService.addUserToUserGroup(user.getId(), userGroup.getId(), USER_IS_OWNER, LocalDate.now());
            project.setUserGroup(userGroup);
        }
        Project saveProject = projectRepository.save(project);
        return mapper.convertToDto(saveProject);
    }

    public Page<Set<ProjectDto>> getProjectsByUser(Long userExternalId, Pageable pageable) {
        User user = userRepository.getByExternalId(userExternalId).orElseThrow(UserNotFoundException::new);
        Set<Project> projects = projectRepository.getProjectsByUserId(user.getId(), pageable);
        return projects.stream().map(mapper::convertToDto).collect(Collectors.toSet());
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
