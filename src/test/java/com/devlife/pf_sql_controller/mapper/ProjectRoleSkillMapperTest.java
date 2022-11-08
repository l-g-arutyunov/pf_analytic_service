package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleSkillDto;
import com.devlife.pf_sql_controller.entity.ProjectRoleSkill;
import com.devlife.pf_sql_controller.entity.embeddable.ProjectRoleSkillId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("ProjectRoleSkill Mapper Tests")
class ProjectRoleSkillMapperTest {
    @Autowired
    ProjectRoleSkillMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО ProjectRoleSkill в Entity")
    void convertToEntityTest_OK() {
        ProjectRoleSkill referenceProjectRoleSkill = new ProjectRoleSkill();
        referenceProjectRoleSkill.setProjectRoleSkillId(null);
        ProjectRoleSkillDto projectRoleSkillDto = ProjectRoleSkillDto.builder()
                .skillId(null)
                .projectRoleId(null)
                .build();
        ProjectRoleSkill projectRoleSkill = mapper.convertToEntity(projectRoleSkillDto);
        assertEquals(referenceProjectRoleSkill,projectRoleSkill);

    }

    @Test
    @DisplayName("Конвертируем Entity ProjectRoleSkill в ДТО")
    void convertToDtoTest_OK() {
        ProjectRoleSkillDto referenceProjectRoleSkillDto = ProjectRoleSkillDto.builder()
                .skillId(null)
                .projectRoleId(null)
                .build();
        ProjectRoleSkill projectRoleSkill= new ProjectRoleSkill();
        projectRoleSkill.setProjectRoleSkillId(null);
        ProjectRoleSkillDto projectRoleSkillDto = mapper.convertToDto(projectRoleSkill);
        assertEquals(referenceProjectRoleSkillDto,projectRoleSkillDto);
    }
}