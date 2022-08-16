package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.SkillDto;
import com.devlife.pf_sql_controller.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/")
public class SkillController {

    private final SkillService service;

    @PutMapping("skill")
    Long addSkill(@RequestBody SkillDto skillDto){
        return service.addSkill(skillDto);
    }

    @GetMapping("skill/{id}")
    SkillDto getSkill(@PathVariable("id") Long id){
        return service.getSkill(id);
    }

    @GetMapping("skill")
    List<SkillDto> getAllSkills(){
        return service.getAllSkills();
    }

    @DeleteMapping("skill/{id}")
    Boolean deleteSkillById(@PathVariable("id") Long id){
        return service.deleteSkillById(id);
    }
}
