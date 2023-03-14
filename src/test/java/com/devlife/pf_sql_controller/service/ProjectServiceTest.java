package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.config.MainConfig;
import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.ProjectRoleDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.dto.apiRequestDto.UpdateProjectByProjectIdReq;
import com.devlife.pf_sql_controller.dto.apiResponseDto.AddProjectMemberRes;
import com.devlife.pf_sql_controller.entity.*;
import com.devlife.pf_sql_controller.eventPublisher.ProjectPublisher;
import com.devlife.pf_sql_controller.exception.BusinessLogicException;
import com.devlife.pf_sql_controller.mapper.ProjectMapper;
import com.devlife.pf_sql_controller.repository.ProjectRepository;
import com.devlife.pf_sql_controller.repository.UserGroupRepository;
import com.devlife.pf_sql_controller.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Test of project service")
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    final ModelMapper modelMapper = new MainConfig().modelMapper();
    @Spy
    private ProjectMapper projectMapper = new ProjectMapper(modelMapper);
    {
        projectMapper.setupMapper();
    }
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EmployerService employerService;
    @Mock
    private UserGroupRepository userGroupRepository;
    @Mock
    private UserGroupUserService userGroupUserService;
    @Mock
    private ProjectRoleService projectRoleService;
    @Mock
    private ProjectPublisher projectPublisher;
    @InjectMocks
    private ProjectService projectService;


    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(projectRepository);
    }

    @Test
    @DisplayName("Add project test with userGroup")
    void addProject_withUserGroup() {
        final UserGroupDto userGroupDto = UserGroupDto.builder()
                .name("testGroup")
                .id(1L)
                .build();
        final ProjectDto projectDto = ProjectDto.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(userGroupDto)
                .startDate(LocalDate.EPOCH)
                .build();
        final Long userExternalId = 1L;
        final User user = User.builder().id(1L).build();
        final UserGroup userGroup = UserGroup.builder()
                .name("testGroup")
                .id(1L)
                .build();
        final Project project = Project.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(userGroup)
                .startDate(LocalDate.EPOCH)
                .build();

        doReturn(Optional.of(user)).when(userRepository).findByExternalId(userExternalId);
        doReturn(true).when(userGroupUserService).userExistInUserGroup(1L, 1L);
        doReturn(project).when(projectRepository).save(project);
        doNothing().when(projectPublisher).sendMessage(any(), any());

        final ProjectDto projectDtoResponse = projectService.addProject(projectDto, 1L);

        assertEquals(projectDto, projectDtoResponse);
        verify(userRepository, times(1)).findByExternalId(userExternalId);
        verify(projectMapper, times(1)).convertToDto(project);
        verify(projectMapper, times(1)).convertToEntity(projectDto);
        verify(projectRepository, times(1)).save(project);
        verify(userGroupUserService, times(1)).userExistInUserGroup(any(), any());
    }

    @Test
    @DisplayName("Add project test without userGroup")
    void addProject_withoutUserGroup() {
        final ProjectDto inputProjectDto = ProjectDto.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();

        final Long userExternalId = 1L;
        final User user = User.builder().id(1L).build();

        final UserGroup userGroup = UserGroup.builder().id(11L).name("testGroup").build();
        final Project project = Project.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(userGroup)
                .startDate(LocalDate.EPOCH)
                .build();

        final ProjectDto refProjectDto = ProjectDto.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(UserGroupDto.builder().id(11L).name("testGroup").build())
                .startDate(LocalDate.EPOCH)
                .build();

        doReturn(Optional.of(user)).when(userRepository).findByExternalId(userExternalId);
        doReturn(userGroup).when(userGroupRepository).save(any(UserGroup.class));
        doReturn(project).when(projectRepository).save(project);
        doNothing().when(projectPublisher).sendMessage(any(), any());

        final ProjectDto projectDtoResponse = projectService.addProject(inputProjectDto, 1L);

        assertEquals(refProjectDto, projectDtoResponse);
        verify(userRepository, times(1)).findByExternalId(userExternalId);
        verify(projectMapper, times(1)).convertToDto(project);
        verify(projectMapper, times(1)).convertToEntity(inputProjectDto);
        verify(userGroupRepository, times(1)).save(any(UserGroup.class));
        verify(projectRepository, times(1)).save(project);
    }

    @Test
    @DisplayName("Get project test")
    void getProjectsByUser() {
        final Pageable pageable = PageRequest.of(0, 2);
        final User user = User.builder().id(1L).build();
        final Long userExternalId = 100L;

        final Project project1 = Project.builder()
                .id(1L)
                .name("testProject1")
                .description("testDescription1")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();
        final Project project2 = Project.builder()
                .id(2L)
                .name("testProject2")
                .description("testDescription2")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();
        final Page<Project> projects = new PageImpl<>(List.of(project1, project2), pageable, 100);

        doReturn(Optional.of(user)).when(userRepository).findByExternalId(userExternalId);
        doReturn(projects).when(projectRepository).getProjectsByUserId(user.getId(), pageable);
        doReturn(100L).when(projectRepository).getCountByUserId(user.getId());

        final Page<ProjectDto> actualProjects = projectService.getProjectsByUser(100L, pageable);
        assertNotNull(actualProjects);
        assertEquals(100, actualProjects.getTotalElements());

        final List<ProjectDto> actualContent = actualProjects.getContent();
        assertNotNull(actualContent);
        assertEquals(2, actualContent.size());

        final ProjectDto actualItem1 = actualContent.get(0);
        assertNotNull(actualItem1);
        assertEquals(1L, actualItem1.getId());
        assertEquals("testProject1", actualItem1.getName());
        assertEquals("testDescription1", actualItem1.getDescription());
        assertEquals(LocalDate.EPOCH, actualItem1.getStartDate());

        final ProjectDto actualItem2 = actualContent.get(1);
        assertNotNull(actualItem1);
        assertEquals(2L, actualItem2.getId());
        assertEquals("testProject2", actualItem2.getName());
        assertEquals("testDescription2", actualItem2.getDescription());
        assertEquals(LocalDate.EPOCH, actualItem2.getStartDate());

        verify(userRepository, times(1)).findByExternalId(userExternalId);
        verify(projectRepository, times(1)).getProjectsByUserId(any(), any(Pageable.class));
        verify(projectRepository, times(1)).getCountByUserId(any());
    }

    @Test
    @DisplayName("Add to project test")
    void addUserToProject_OK() {
        final Long projectId = 1L;
        final User user = User.builder().id(2L).externalId(3L).build();
        Set<AddProjectMemberReq> addProjectMemberReqSet = Collections.singleton(AddProjectMemberReq.builder()
                .startDate(LocalDate.of(2022, 1, 1))
                .endDate(LocalDate.of(2022, 1, 31))
                .roleId(3L)
                .roleLevel("test")
                .userExternalId(3L)
                .description("description1")
                .build());
        Map<User, Set<AddProjectMemberReq>> filteredUsersInputDataMap = Collections.singletonMap(user, addProjectMemberReqSet);
        final Project project1 = Project.builder()
                .id(1L)
                .name("testProject1")
                .description("testDescription1")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();
        final ProjectRoleDto projectRoleDto = ProjectRoleDto.builder().id(10L).build();

        doReturn(Collections.singleton(user)).when(userRepository).getUsersByExternalIdIn(Collections.singleton(3L));
        doReturn(Optional.of(project1)).when(projectRepository).findById(projectId);
        doReturn(Collections.singleton(projectRoleDto)).when(projectRoleService).addUserToProject(project1, filteredUsersInputDataMap);

        Set<AddProjectMemberRes> answer = projectService.addUserToProject(projectId, addProjectMemberReqSet);

        assertNotNull(answer);
        assertTrue(answer.size() > 0);

        verify(userRepository, times(1)).getUsersByExternalIdIn(any());
        verify(projectRepository, times(1)).findById(any());
        verify(projectRoleService, times(1)).addUserToProject(any(), any());
    }

    @Test
    @DisplayName("Add to project test")
    void addUserToProject_ExceptionWithDatesProject() {
        final Long projectId = 1L;
        final User user = User.builder().id(2L).externalId(3L).build();
        Set<AddProjectMemberReq> addProjectMemberReqSet = Collections.singleton(AddProjectMemberReq.builder()
                .startDate(LocalDate.of(2022, 1, 1))
                .endDate(LocalDate.of(2022, 1, 31))
                .roleId(3L)
                .roleLevel("test")
                .userExternalId(3L)
                .description("description1")
                .build());
        final Project project1 = Project.builder()
                .id(1L)
                .name("testProject1")
                .description("testDescription1")
                .userGroup(null)
                .startDate(LocalDate.of(2022, 1, 2))
                .build();

        doReturn(Collections.singleton(user)).when(userRepository).getUsersByExternalIdIn(Collections.singleton(3L));
        doReturn(Optional.of(project1)).when(projectRepository).findById(projectId);

        assertThrowsExactly(BusinessLogicException.class, () -> projectService.addUserToProject(projectId, addProjectMemberReqSet));
    }

    @Test
    void updateProjectByProjectId_OK() {
        UpdateProjectByProjectIdReq updateProjectByProjectIdReq = UpdateProjectByProjectIdReq.builder()
                .projectTypeId(3L)
                .employerId(4L)
                .startDate(LocalDate.MIN)
                .endDate(LocalDate.MAX)
                .description("testDescr")
                .name("testName")
                .build();

        final Project project1 = Project.builder()
                .id(1L)
                .name("testProject1")
                .description("testDescription1")
                .userGroup(UserGroup.builder().id(2L).build())
                .startDate(LocalDate.of(2022, 1, 2))
                .build();

        final Project projectUpdate = Project.builder()
                .id(1L)
                .name("testName")
                .description("testDescr")
                .userGroup(UserGroup.builder().id(2L).build())
                .startDate(LocalDate.MIN)
                .endDate(LocalDate.MAX)
                .projectType(ProjectType.builder().id(3L).build())
                .employer(Employer.builder().id(4L).build())
                .build();

        doReturn(Optional.of(project1)).when(projectRepository).findById(1L);
        doReturn(true).when(employerService).checkUserGroupEmployer(updateProjectByProjectIdReq.getEmployerId(), project1.getUserGroup());
        doReturn(projectUpdate).when(projectRepository).save(any());
        doNothing().when(projectPublisher).sendMessage(any(), any());

        projectService.updateProjectByProjectId(1L, updateProjectByProjectIdReq);

        Project projectRef = Project.builder()
                .projectType(ProjectType.builder().id(3L).build())
                .employer(Employer.builder().id(4L).build())
                .startDate(LocalDate.MIN)
                .endDate(LocalDate.MAX)
                .userGroup(UserGroup.builder().id(2L).build())
                .description("testDescr")
                .name("testName")
                .id(1L)
                .build();

        verify(projectRepository, times(1)).findById(1L);
        verify(employerService, times(1)).checkUserGroupEmployer(updateProjectByProjectIdReq.getEmployerId(), project1.getUserGroup());
        verify(projectRepository, times(1)).save(projectRef);
    }
}

