package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.config.MainConfig;
import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Project;
import com.devlife.pf_sql_controller.entity.User;
import com.devlife.pf_sql_controller.entity.UserGroup;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("Test of project service")
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    final ModelMapper modelMapper = new MainConfig().modelMapper();
    @Spy
    private ProjectMapper projectMapper = new ProjectMapper(modelMapper);
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserGroupRepository userGroupRepository;
    @Mock
    private UserGroupUserService userGroupUserService;
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

        doReturn(Optional.of(user)).when(userRepository).getByExternalId(userExternalId);
        //when(userRepository.getByExternalId(userExternalId)).thenReturn(Optional.of(user)); TODO Примера ради
        doReturn(true).when(userGroupUserService).userExistInUserGroup(1L, 1L);
        doReturn(project).when(projectRepository).save(project);

        final ProjectDto projectDtoResponse = projectService.addProject(projectDto, 1L);

        assertEquals(projectDto, projectDtoResponse);
        verify(userRepository, times(1)).getByExternalId(userExternalId);
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

        doReturn(Optional.of(user)).when(userRepository).getByExternalId(userExternalId);
        doReturn(userGroup).when(userGroupRepository).save(any(UserGroup.class));
        doReturn(project).when(projectRepository).save(project);

        final ProjectDto projectDtoResponse = projectService.addProject(inputProjectDto, 1L);

        assertEquals(refProjectDto, projectDtoResponse);
        verify(userRepository, times(1)).getByExternalId(userExternalId);
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

        doReturn(Optional.of(user)).when(userRepository).getByExternalId(userExternalId);
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

        verify(userRepository, times(1)).getByExternalId(userExternalId);
        verify(projectRepository, times(1)).getProjectsByUserId(any(), any(Pageable.class));
        verify(projectRepository, times(1)).getCountByUserId(any());
    }
}

