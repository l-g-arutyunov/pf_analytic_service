package com.devlife.pf_sql_controller.controller.v1;


import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.service.RoleTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/roleType")
public class RoleTypeController {

    private final RoleTypeService service;

    @PutMapping
    @Operation(summary = "Add or update role type", tags = {"roleType"})
    ResponseEntity<RoleTypeDto> addRoleType(@RequestBody RoleTypeDto roleTypeDto ) {
        return  ResponseEntity.ok(service.addRoleType(roleTypeDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get role type by id", tags = {"roleType"})
    ResponseEntity<RoleTypeDto> getProjectType(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getRoleType(id));
    }

    @GetMapping
    @Operation(summary = "Get all role type", tags = {"roleType"})
    ResponseEntity<List<RoleTypeDto>> getAllProjectType() {
        return ResponseEntity.ok(service.getAllRoleTypes());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete role type by id", tags = {"roleType"})
    @PreAuthorize("hasAuthority('ROOT')")
    HttpStatus deleteProjectTypeById(@PathVariable("id") Long id) {
        service.deleteRoleTypeById(id);
        return HttpStatus.OK;
    }

}
