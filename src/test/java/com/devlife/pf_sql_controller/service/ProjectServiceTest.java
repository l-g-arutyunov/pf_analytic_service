package com.devlife.pf_sql_controller.service;

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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Test of project service")
@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ProjectMapper projectMapper;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    private UserGroupRepository userGroupRepository;
    @Mock
    private UserGroupUserService userGroupUserService;

    @InjectMocks
    private ProjectService projectService;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(projectRepository);
        verifyNoMoreInteractions(projectMapper);
    }

    @Test
    @DisplayName("Test of project service with userGroup")
    void addProject_withUserGroup() {
        UserGroupDto userGroupDto = UserGroupDto.builder()
                .name("testGroup")
                .id(1L)
                .build();
        ProjectDto projectDto = ProjectDto.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(userGroupDto)
                .startDate(LocalDate.EPOCH)
                .build();
        Long userExternalId = 1L;
        User user = User.builder().id(1L).build();
        UserGroup userGroup = UserGroup.builder()
                .name("testGroup")
                .id(1L)
                .build();
        Project project = Project.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(userGroup)
                .startDate(LocalDate.EPOCH)
                .build();

        doReturn(Optional.of(user)).when(userRepository).getByExternalId(userExternalId);
        //when(userRepository.getByExternalId(userExternalId)).thenReturn(Optional.of(user)); TODO Примера ради
        doReturn(true).when(userGroupUserService).userExistInUserGroup(1L, 1L);
        doReturn(project).when(projectMapper).convertToEntity(projectDto);
        doReturn(projectDto).when(projectMapper).convertToDto(project);
        doReturn(project).when(projectRepository).save(project);

        ProjectDto projectDtoResponse = projectService.addProject(projectDto, 1L);

        assertEquals(projectDtoResponse, projectDto);
        verify(userRepository, times(1)).getByExternalId(userExternalId);
        verify(projectMapper, times(1)).convertToDto(project);
        verify(projectMapper, times(1)).convertToEntity(projectDto);
        verify(projectRepository, times(1)).save(project);
        verify(userGroupUserService, times(1)).userExistInUserGroup(any(), any());
    }

    @Test
    @DisplayName("Test of project service without userGroup")
    void addProject_withoutUserGroup() {
        ProjectDto projectDto = ProjectDto.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();

        Long userExternalId = 1L;
        User user = User.builder().id(1L).build();

        Project project = Project.builder()
                .name("testProject")
                .description("testDescription")
                .userGroup(null)
                .startDate(LocalDate.EPOCH)
                .build();

        UserGroup userGroup = UserGroup.builder().name("testGroup").build();

        doReturn(Optional.of(user)).when(userRepository).getByExternalId(userExternalId);
        doReturn(project).when(projectMapper).convertToEntity(projectDto);
        doReturn(projectDto).when(projectMapper).convertToDto(project);
        doReturn(userGroup).when(userGroupRepository).save(any(UserGroup.class));
        doReturn(project).when(projectRepository).save(project);

        ProjectDto projectDtoResponse = projectService.addProject(projectDto, 1L);

        assertEquals(projectDtoResponse, projectDto);
        verify(userRepository, times(1)).getByExternalId(userExternalId);
        verify(projectMapper, times(1)).convertToDto(project);
        verify(projectMapper, times(1)).convertToEntity(projectDto);
        verify(userGroupRepository, times(1)).save(any(UserGroup.class));
        verify(projectRepository, times(1)).save(project);
    }
}

