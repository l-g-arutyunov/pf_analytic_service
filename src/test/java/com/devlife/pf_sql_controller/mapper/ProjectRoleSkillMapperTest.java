package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.ProjectRoleSkillDto;
import com.devlife.pf_sql_controller.entity.ProjectRoleSkill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("ProjectRoleSkill Mapper Tests")
class ProjectRoleSkillMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
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