package com.devlife.pf_sql_controller.mapper;

import com.devlife.pf_sql_controller.dto.SkillDto;
import com.devlife.pf_sql_controller.entity.Skill;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(MockitoExtension.class)
@DisplayName("Skill mapper tests")
class SkillMapperTest {
    @Spy
    ModelMapper modelMapper;
    @InjectMocks
    SkillMapper mapper;

    @Test
    @DisplayName("Конвертируем ДТО Скилла в Entity")
    void convertDtoToEntityTest_OK() {
        Skill referenceSkillEntity= new Skill();
        referenceSkillEntity.setId(1L);
        referenceSkillEntity.setName("Test");
        referenceSkillEntity.setExternalId(1L);
        referenceSkillEntity.setDescription("Описание");

        SkillDto skillDto = SkillDto.builder()
                .id(1L)
                .name("Test")
                .description("Описание")
                .externalId(1L)
                .build();

        Skill skill = mapper.convertToEntity(skillDto);
        assertEquals(referenceSkillEntity,skill);
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