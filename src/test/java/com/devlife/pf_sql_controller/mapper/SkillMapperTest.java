package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.SkillDto;
import com.devlife.pf_sql_controller.entity.Skill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("dev")
@DisplayName("Skill mapper tests")
class SkillMapperTest {
    @Autowired
    SkillMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО Скилла в Entity")
    void convertDtoToEntityTest_OK() {
        Skill skillEntity= new Skill();
        skillEntity.setId(1L);
        skillEntity.setName("Test");
        skillEntity.setExternalId(1L);
        skillEntity.setDescription("Описание");

        SkillDto skillDto = SkillDto.builder()
                .id(1L)
                .name("Test")
                .description("Описание")
                .externalId(1L)
                .build();

        Skill skill = mapper.convertToEntity(skillDto);
        assertEquals(skillEntity,skill);
    }

    @Test
    @DisplayName("Конвертируем Entity Скилла в ДТО")
    void convertEntityToDtoTest_OK() {
        SkillDto referenceSkillDto = SkillDto.builder()
                .id(1L)
                .name("Test")
                .description("Описание")
                .externalId(1L)
                .build();

        Skill skillEntity= new Skill();
        skillEntity.setId(1L);
        skillEntity.setName("Test");
        skillEntity.setExternalId(1L);
        skillEntity.setDescription("Описание");

        SkillDto skillDto = mapper.convertToDto(skillEntity);
        assertEquals(referenceSkillDto,skillDto);
    }
}