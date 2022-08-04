package com.devlife.pf_sql_controller.mapper;


import com.devlife.pf_sql_controller.dto.RoleTypeDto;
import com.devlife.pf_sql_controller.entity.RoleType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleTypeMapper {
    private final ModelMapper mapper;

       public RoleType convertToEntity(RoleTypeDto roleTypeDto) {
           return mapper.map(roleTypeDto,RoleType.class);

       }
    public RoleTypeDto convertToDto(RoleType roleTypeEntity) {
        return mapper.map(roleTypeEntity,RoleTypeDto.class);
    }
}
