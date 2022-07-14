package com.devlife.pf_sql_controller.controller.v1;


import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.service.RoleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class RoleTypeController {

    private final RoleTypeService service;


    @PutMapping("roletype")
    Long addRoleType(@RequestBody RoleTypeDto roleTypeDto ) {
        return service.addRoleType(roleTypeDto);
    }

    @GetMapping("roletype/{id}")
    RoleTypeDto getRoleType(@PathVariable("id") Long id) {
        return service.getRoleType(id);
    }

    @GetMapping("roletype")
    List<RoleTypeDto> getAllRoleTypes(){
        return service.getAllRoleTypes();
    }

    @DeleteMapping("roletype/{id}")
    Boolean deleteRoleTypeById(@PathVariable("id") Long id){
        return service.deleteRoleTypeById(id);
    }

}
