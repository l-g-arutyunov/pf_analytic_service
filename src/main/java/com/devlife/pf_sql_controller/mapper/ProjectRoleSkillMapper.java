package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleSkillDto;
import com.devlife.pf_sql_controller.entity.ProjectRoleSkill;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

@Component
public class ProjectRoleSkillMapper {
    private final ModelMapper mapper;

    public ProjectRoleSkillMapper(ModelMapper mapper) {
        this.mapper = mapper;
        mapper.addMappings(new PropertyMap<ProjectRoleSkillDto, ProjectRoleSkill>() {
            @Override
            protected void configure() {
                map().getProjectRoleSkillId().setProjectRoleId(source.getProjectRoleId());
                map().getProjectRoleSkillId().setSkillId(source.getSkillId());
            }
        });
    }

    public ProjectRoleSkill convertToEntity(ProjectRoleSkillDto projectRoleSkillDto) {
        return mapper.map(projectRoleSkillDto, ProjectRoleSkill.class);
    }

    public ProjectRoleSkillDto convertToDto(ProjectRoleSkill projectRoleSkillEntity) {
        return mapper.map(projectRoleSkillEntity, ProjectRoleSkillDto.class);
    }
}
