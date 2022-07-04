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


    @PutMapping("roleType")
    Long addRoleType(@RequestBody RoleTypeDto roleTypeDto ) {
        return service.addRoleType(roleTypeDto);
    }

    @GetMapping("roleType/{id}")
    RoleTypeDto getRoleType(@PathVariable("id") Long id) {
        return service.getRoleType(id);
    }

    @GetMapping("roleType")
    List<RoleTypeDto> getAllRoleTypes(){
        return service.getAllRoleTypes();
    }

    @DeleteMapping("roleType/{id}")
    Boolean deleteRoleTypeById(@PathVariable("id") Long id){
        return service.deleteRoleTypeById(id);
    }

}
