package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.dto.apiRequestDto.UpdateProjectByProjectIdReq;
import com.devlife.pf_sql_controller.dto.apiResponseDto.AddProjectMemberRes;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.exception.BusinessLogicException;
import com.devlife.pf_sql_controller.exception.ProjectNotFoundException;
import com.devlife.pf_sql_controller.exception.UserNotFoundException;
import com.devlife.pf_sql_controller.mapper.ProjectMapper;
import com.devlife.pf_sql_controller.repository.ProjectRepository;
import com.devlife.pf_sql_controller.repository.UserGroupRepository;
import com.devlife.pf_sql_controller.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    public static final String USER_GROUP_OF = "User group of %s1";
    public static final String PARTICIPATION_DATES_NOT_IN_PROJECT_DATES = "Participation dates are not in the project dates for %s1";
    public static final String USER_GROUP_IN_PROJECT_NOT_EQUALS_USER_GROUP_IN_EMPLOYER = "User group in project (userGroupId: %d1), isn't equals user group in employer";
    public static final boolean USER_IS_OWNER = true;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final EmployerService employerService;
    private final UserGroupRepository userGroupRepository;
    private final ProjectMapper projectMapper;
    private final UserGroupUserService userGroupUserService;
    private final ProjectRoleService projectRoleService;

    @Transactional
    public ProjectDto addProject(ProjectDto projectDto, Long userExternalId) {
        final User user = userRepository.findByExternalId(userExternalId).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("Id is %d0", userExternalId));
        });
        final Project project = projectMapper.convertToEntity(projectDto);
        if (project.getUserGroup() != null && !userGroupUserService.userExistInUserGroup(user.getId(), project.getUserGroup().getId())) {
            throw new IllegalArgumentException("User not exist in the user group");
        }
        if (project.getUserGroup() == null) {
            final UserGroup userGroup = userGroupRepository.save(
                    UserGroup.builder()
                            .name(String.format(USER_GROUP_OF, project.getName()))
                            .build()
            );
            userGroupUserService.addUserToUserGroup(user.getId(), userGroup.getId(), USER_IS_OWNER, LocalDate.now());
            project.setUserGroup(userGroup);
        }
        final Project saveProject = projectRepository.save(project);
        return projectMapper.convertToDto(saveProject);
    }

    public Page<ProjectDto> getProjectsByUser(Long userExternalId, Pageable pageable) {
        final User user = userRepository.findByExternalId(userExternalId).orElseThrow(UserNotFoundException::new);
        final Page<Project> projects = projectRepository.getProjectsByUserId(user.getId(), pageable);
        final List<ProjectDto> projectDtoList = projects.getContent().stream().map(projectMapper::convertToDto).collect(Collectors.toList());
        final Long countProjects = projectRepository.getCountByUserId(user.getId());
        return new PageImpl<>(
                projectDtoList, pageable, countProjects
        );
    }

    public List<ProjectDto> getAllProjects() {
        List<Project> projectsList = projectRepository.findAll();
        return projectsList.stream()
                .map(projectMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public Boolean deleteProjectById(Long id) {
        projectRepository.deleteById(id);
        return !projectRepository.existsById(id);
    }

    @Transactional
    public Set<AddProjectMemberRes> addUserToProject(Long projectId, Set<AddProjectMemberReq> addProjectMemberReqSet) {
        final Map<Long, AddProjectMemberReq> helpMapOfExternalId = addProjectMemberReqSet.stream().collect(
                Collectors.toMap(AddProjectMemberReq::getUserExternalId, UnaryOperator.identity())
        );

        final Set<User> users = userRepository.getUsersByExternalIdIn(
                addProjectMemberReqSet.stream()
                        .map(AddProjectMemberReq::getUserExternalId)
                        .collect(Collectors.toSet())
        );
        final Set<Long> notFoundUsersId = new HashSet<>();

        Map<User, Set<AddProjectMemberReq>> filteredUsersInputDataMap = helpMapOfExternalId.entrySet().stream()
                .filter(entry -> {
                    if (users.stream().noneMatch(user -> user.getExternalId().equals(entry.getKey()))) {
                        notFoundUsersId.add(entry.getKey());
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.groupingBy(
                        entry -> users.stream().filter(user -> user.getExternalId().equals(entry.getKey()))
                                .findAny().orElseThrow(() -> {
                                    throw new BusinessLogicException("impossible event");
                                }),
                        Collectors.mapping(Map.Entry::getValue, Collectors.toSet()))
                );

        if (!notFoundUsersId.isEmpty()) {
            throw new UserNotFoundException(notFoundUsersId.toArray(String[]::new));
        }

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new ProjectNotFoundException("id: " + projectId));
        checkDatesOfUsersAddToProject(project, filteredUsersInputDataMap);
        Set<ProjectRoleDto> projectRoleDtoSet = projectRoleService.addUserToProject(project, filteredUsersInputDataMap);
        return projectRoleDtoSet.stream()
                .map(i -> AddProjectMemberRes.builder().projectRole(i).build())
                .collect(Collectors.toSet());
    }

    private void checkDatesOfUsersAddToProject(Project project, Map<User, Set<AddProjectMemberReq>> filteredUsersInputDataMap) {
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate() == null ? LocalDate.MAX : project.getEndDate();

        final Map<User, AddProjectMemberReq> dataForException = new HashMap<>();
        for (Map.Entry<User, Set<AddProjectMemberReq>> entry : filteredUsersInputDataMap.entrySet()) {
            entry.getValue().forEach(i -> {
                if ((i.getEndDate() != null && i.getEndDate().isAfter(endDate))
                        || i.getStartDate().isBefore(startDate)) {
                    dataForException.put(entry.getKey(), i);
                }
            });

        }
        if (!dataForException.isEmpty()) {
            throw new BusinessLogicException(String.format(PARTICIPATION_DATES_NOT_IN_PROJECT_DATES, dataForException));
        }
    }

    @Transactional
    public ProjectDto updateProjectByProjectId(Long projectId, UpdateProjectByProjectIdReq updateProjectByProjectIdReq) {
        final Project project = projectRepository.findById(projectId).orElseThrow(() ->  new ProjectNotFoundException("id: " + projectId));
        if (updateProjectByProjectIdReq.getEmployerId() != null
                && !employerService.checkUserGroupEmployer(updateProjectByProjectIdReq.getEmployerId(), project.getUserGroup())) {
                    throw new BusinessLogicException(String.format(USER_GROUP_IN_PROJECT_NOT_EQUALS_USER_GROUP_IN_EMPLOYER, project.getUserGroup().getId()));
        }

        Project projectUpdate = projectMapper.convertUpdateProjectByProjectIdReqToEntity(updateProjectByProjectIdReq, project.getId(), project.getUserGroup());

        if (projectUpdate.getProjectType() == null)  projectUpdate.setProjectType(project.getProjectType());
        if (projectUpdate.getName() == null)  projectUpdate.setName(project.getName());
        if (projectUpdate.getEmployer() == null) projectUpdate.setEmployer(project.getEmployer());
        if (projectUpdate.getDescription() == null) projectUpdate.setDescription(project.getDescription());
        if (projectUpdate.getStartDate() == null) projectUpdate.setStartDate(project.getStartDate());
        if (projectUpdate.getEndDate() == null) projectUpdate.setEndDate(project.getEndDate());

        return projectMapper.convertToDto(projectRepository.save(projectUpdate));
    }
}
