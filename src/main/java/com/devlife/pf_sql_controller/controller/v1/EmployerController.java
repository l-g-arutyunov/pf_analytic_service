package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.service.EmployerService;
import com.querydsl.core.types.Predicate;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/employer")
public class EmployerController {

    private final EmployerService service;

    @PutMapping
    @Operation(summary = "Add or update employer", tags = {"employer"})
    ResponseEntity<EmployerDto> addEmployer(@RequestBody EmployerDto employerDto) {
        return ResponseEntity.ok(service.addEmployer(employerDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employer by id", tags = {"employer"})
    ResponseEntity<EmployerDto> getEmployer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getEmployer(id));
    }

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
                    , content = @Content(array = @ArraySchema(schema = @Schema(type = "string")))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "Employer name"
                    , name = "name"
                    , content = @Content(schema = @Schema(type = "string"))),
            @Parameter(in = ParameterIn.QUERY
                    , description = "user group id"
                    , name = "userGroupId"
                    , content = @Content(schema = @Schema(type = "integer")))
    })
    @GetMapping
    @Operation(summary = "Get all employer by userGroupId", tags = {"employer"})
    ResponseEntity<Page<EmployerDto>> getAllEmployers(
            @RequestParam
            @Parameter(hidden = true, description = "Параметры предиката определены в блоке @Parameters")
            Predicate predicate,
            @RequestParam
            @Parameter(hidden = true, description = "Параметры сортировки определены в блоке @Parameters")
            Pageable pageable
    ) {
        return ResponseEntity.ok(service.getAllEmployers(predicate, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete employer by id", tags = {"employer"})
    HttpStatus deleteEmployerById(@PathVariable("id") Long id) {
        service.deleteEmployerById(id);
        return HttpStatus.OK;
    }
}
