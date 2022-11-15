package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.ProjectDto;
import com.devlife.pf_sql_controller.dto.apiRequestDto.AddProjectMemberReq;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class ProjectController {
    private final ProjectService service;

    @PutMapping("project/{externalUserId}")
    @Operation(summary = "Add projects by userId", tags = {"project"})
    ResponseEntity<ProjectDto> addProject(
            @PathVariable Long externalUserId,
            @RequestBody ProjectDto projectDto) {
        return ResponseEntity.ok(service.addProject(projectDto, externalUserId));
    }

    @GetMapping("project/{externalUserId}")
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
            @PathVariable Long externalUserId,
            @Parameter(hidden = true, description = "Параметры сортировки определены в блоке @Parameters")
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getProjectsByUser(externalUserId, pageable));
    }

    @PutMapping("project/{projectId}/members")
    @Operation(summary = "Add members to the project", tags = {"project", "member"})
    ResponseEntity<Set<AddProjectMemberRes>> addProjectMember(
            @Parameter(description = "external user id", required = true, name = "projectId")
            @PathVariable Long projectId,
            @Parameter(description = "userId and projectRoleId to set", required = true, name = "addProjectMemberReqList")
            @RequestBody Set<AddProjectMemberReq> addProjectMemberReqSet
    ) {
        service.addUserToProject(projectId, addProjectMemberReqSet);
        return null;
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @GetMapping("project")
    List<ProjectDto> getAllProject() {
        return service.getAllProjects();
    }

    @PreAuthorize("hasAuthority('ROOT')")
    @DeleteMapping("project/{id}")
    Boolean deleteProjectById(@PathVariable("id") Long id) {
        return service.deleteProjectById(id);
    }


}
