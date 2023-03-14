package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.SkillDto;
import com.devlife.pf_sql_controller.entity.Skill;
import com.devlife.pf_sql_controller.mapper.SkillMapper;
import com.devlife.pf_sql_controller.repository.SkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class SkillService {
    private final SkillRepository skillRepository;
    private final SkillMapper mapper;

    public Long addSkill(SkillDto skill) {
        Skill saveSkill = skillRepository.save(mapper.convertToEntity(skill));
        if(saveSkill != null) {
            return saveSkill.getId();
        }
        return null;
    }
    public SkillDto getSkill(Long id) {
        Skill skill = skillRepository.getById(id);
        return mapper.convertToDto(skill);
    }
    public List<SkillDto> getAllSkills(){
        List<Skill> skillList = skillRepository.findAll();
        return skillList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }
    public Boolean deleteSkillById(Long id) {
        skillRepository.deleteById(id);
        return !skillRepository.existsById(id);
    }
}
