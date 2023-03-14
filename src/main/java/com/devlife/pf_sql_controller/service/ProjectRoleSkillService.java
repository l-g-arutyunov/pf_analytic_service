package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.ProjectRoleSkillDto;
import com.devlife.pf_sql_controller.entity.ProjectRoleSkill;
import com.devlife.pf_sql_controller.mapper.ProjectRoleSkillMapper;
import com.devlife.pf_sql_controller.repository.ProjectRoleSkillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProjectRoleSkillService {
    private final ProjectRoleSkillRepository projectRoleSkillRepository;
    private final ProjectRoleSkillMapper mapper;

    public void addProjectRoleSkill(ProjectRoleSkillDto projectRoleSkill) {
        projectRoleSkillRepository.save(mapper.convertToEntity(projectRoleSkill));
    }
    public ProjectRoleSkillDto getProjectRoleSkill(Long id) {
        ProjectRoleSkill projectRoleSkill = projectRoleSkillRepository.getById(id);
        return mapper.convertToDto(projectRoleSkill);
    }
    public List<ProjectRoleSkillDto> getAllProjectRoleSkills() {
        List<ProjectRoleSkill> projectRoleSkillList = projectRoleSkillRepository.findAll();
        return projectRoleSkillList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }
    public Boolean deleteProjectRoleSkillById(Long id) {
        projectRoleSkillRepository.deleteById(id);
        return !projectRoleSkillRepository.existsById(id);
    }
}
