package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectReq;
import com.devlife.pf_sql_controller.dto.apiRequestDto.UpdateProjectByProjectIdReq;
import com.devlife.pf_sql_controller.dto.apiResponseDto.AddProjectMemberRes;
import com.devlife.pf_sql_controller.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("api/v1/project")
public class ProjectController {
    private final ProjectService service;

    @PostMapping("/{externalUserId}")
    @Operation(summary = "Add projects by userId", tags = {"project"})
    ResponseEntity<ProjectDto> addProject(
            @PathVariable @NotNull Long externalUserId,
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            @RequestBody
            @Valid AddProjectReq addProjectReq) {
        return ResponseEntity.ok(service.addProject(addProjectReq, externalUserId));
    }

    @GetMapping("/user/{externalUserId}")
    @Operation(summary = "Get projects by userId", tags = {"project"})
    @Parameters({
            @Parameter(in = ParameterIn.QUERY
                    , description = "Page you want to retrieve (0..N)"
                    , name = "page"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "0"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Number of records per page."
                    , name = "size"
                    , content = @Content(schema = @Schema(type = "integer", defaultValue = "20"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Sorting criteria in the format: property(,asc|desc). "
                    + "Default sort order is ascending. Multiple sort criteria are supported."
                    , name = "sort"
                    , content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))))
    })
    ResponseEntity<Page<ProjectDto>> getProjectsByUser(
            @Parameter(description = "external user id", required = true, name = "externalUserId")
            @PathVariable @NotNull Long externalUserId,
            @Parameter(hidden = true, description = "Параметры сортировки определены в блоке @Parameters")
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getProjectsByUser(externalUserId, pageable));
    }

    @GetMapping("/{projectId}")
    @Operation(summary = "Get project by projectId", tags = {"project"})
    ResponseEntity<ProjectDto> getProjectById(
            @Parameter(name = "projectId", description = "project id", required = true)
            @PathVariable @NotNull Long projectId
    ) {
        return ResponseEntity.ok(service.getProjectById(projectId));
    }

    @PutMapping("/{projectId}")
    @Operation(summary = "Update projects by projectId", tags = {"project"})
    ResponseEntity<ProjectDto> updateProject(
            @Parameter(description = "project id", required = true, name = "projectId")
            @PathVariable @NotNull Long projectId,
            @RequestBody
            @io.swagger.v3.oas.annotations.parameters.RequestBody
            UpdateProjectByProjectIdReq updateProjectByProjectIdReq
    ) {
        return ResponseEntity.ok(service.updateProjectByProjectId(projectId, updateProjectByProjectIdReq));
    }

    @PostMapping("/{projectId}/members")
    @Operation(summary = "Add members to the project", tags = {"project"})
    ResponseEntity<Set<AddProjectMemberRes>> addProjectMember(
            @Parameter(description = "project id", required = true, name = "projectId")
            @PathVariable @NotNull Long projectId,
            @Parameter(description = "userId and projectRoleId to set", required = true, name = "addProjectMemberReqSet")
            @RequestBody Set<AddProjectMemberReq> addProjectMemberReqSet
    ) {
        return ResponseEntity.ok(service.addUserToProject(projectId, addProjectMemberReqSet));
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("/")
    List<ProjectDto> getAllProject() {
        return service.getAllProjects();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("/{id}")
    Boolean deleteProjectById(@PathVariable("id") Long id) {
        return service.deleteProjectById(id);
    }

}
