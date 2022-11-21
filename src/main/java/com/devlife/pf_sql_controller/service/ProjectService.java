package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.exception.BusinessLogicException;
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

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    public static final String USER_GROUP_OF = "User group of %s1";
    public static final String PARTICIPATION_DATES_NOT_IN_PROJECT_DATES = "Participation dates are not in the project dates for %s1";
    public static final boolean USER_IS_OWNER = true;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;
    private final ProjectMapper mapper;
    private final UserGroupUserService userGroupUserService;

    @Transactional
    public ProjectDto addProject(ProjectDto projectDto, Long userExternalId) {
        final User user = userRepository.getByExternalId(userExternalId).orElseThrow(() -> {
            throw new UserNotFoundException(String.format("Id is %d0", userExternalId));
        });
        final Project project = mapper.convertToEntity(projectDto);
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
        return mapper.convertToDto(saveProject);
    }

    public Page<ProjectDto> getProjectsByUser(Long userExternalId, Pageable pageable) {
        final User user = userRepository.getByExternalId(userExternalId).orElseThrow(UserNotFoundException::new);
        final Page<Project> projects = projectRepository.getProjectsByUserId(user.getId(), pageable);
        final List<ProjectDto> projectDtoList = projects.getContent().stream().map(mapper::convertToDto).collect(Collectors.toList());
        final Long countProjects = projectRepository.getCountByUserId(user.getId());
        return new PageImpl<>(
                projectDtoList, pageable, countProjects
        );
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

    @Transactional
    public void addUserToProject(Long projectId, Set<AddProjectMemberReq> addProjectMemberReqSet) {
        final Map<Long, AddProjectMemberReq> helpMapOfExternalId = addProjectMemberReqSet.stream().collect(
                Collectors.toMap(AddProjectMemberReq::getUserExternalId, UnaryOperator.identity())
        );

        final Set<User> users = userRepository.getUsersByExternalIdIn(
                addProjectMemberReqSet.stream()
                        .map(AddProjectMemberReq::getUserExternalId)
                        .collect(Collectors.toSet())
        );
        final Set<Long> notFoundUsersId = new HashSet<>();

        Map<User, AddProjectMemberReq> filteredUsersInputDataMap = helpMapOfExternalId.entrySet().stream()
                .filter(entry -> {
                    if (users.stream().noneMatch(user -> user.getExternalId().equals(entry.getKey()))) {
                        notFoundUsersId.add(entry.getKey());
                        return false;
                    }
                    return true;
                })
                .collect(Collectors.toMap(
                        entry -> users.stream().filter(user -> user.getExternalId().equals(entry.getKey()))
                                .findAny().orElseThrow(() -> {
                                    throw new BusinessLogicException("impossible event");
                                }),
                        Map.Entry::getValue)
                );

        if (!notFoundUsersId.isEmpty()) {
            throw new UserNotFoundException(notFoundUsersId.stream()
                    .toArray(String[]::new));
        }

        Project project = projectRepository.getById(projectId);
        checkDatesOfUsersAddToProject(project, filteredUsersInputDataMap);


    }

    private void checkDatesOfUsersAddToProject(Project project, Map<User, AddProjectMemberReq> filteredUsersInputDataMap) {
        LocalDate startDate = project.getStartDate();
        LocalDate endDate = project.getEndDate();

        final Map<User, AddProjectMemberReq> dataForException = new HashMap<>();
        for (Map.Entry<User, AddProjectMemberReq> entry : filteredUsersInputDataMap.entrySet()) {
            if (entry.getValue().getEndDate().isAfter(endDate)
            || entry.getValue().getStartDate().isBefore(startDate)) {
                dataForException.put(entry.getKey(), entry.getValue());
            }
        }
        if (!dataForException.isEmpty()) {
            throw new BusinessLogicException(String.format(PARTICIPATION_DATES_NOT_IN_PROJECT_DATES, dataForException));
        }
    }
}
