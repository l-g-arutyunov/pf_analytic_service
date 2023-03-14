package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.dto.UserGroupDto;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.exception.EmployerNotFoundException;
import com.devlife.pf_sql_controller.mapper.EmployerMapper;
import com.devlife.pf_sql_controller.repository.EmployerRepository;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final EmployerMapper mapper;

    public EmployerDto addEmployer(EmployerDto employer) {
        Employer saveEmployer = employerRepository.save(mapper.convertToEntity(employer));
        return mapper.convertToDto(saveEmployer);
    }

    public EmployerDto getEmployer(Long id) {
        Optional<Employer> employerOpt = employerRepository.findById(id);
        Employer employer = employerOpt.orElseThrow(() -> new EmployerNotFoundException("id : " + id));
        return mapper.convertToDto(employer);
    }

    public Page<EmployerDto> getAllEmployers(Predicate predicate, Pageable pageable) {
        Page<Employer> employers = employerRepository.findAll(predicate, pageable);
        List<EmployerDto> employerDtoList = employers.getContent().stream().map(mapper::convertToDto).collect(Collectors.toList());
        //todo Проверить
        Long countEmployers = employerRepository.countAllByUserGroupId(employerDtoList.stream()
                .map(EmployerDto::getUserGroup)
                .filter(Objects::nonNull)
                .map(UserGroupDto::getId)
                .collect(Collectors.toSet())
        );
        return new PageImpl<>(
                employerDtoList, pageable, countEmployers
        );
    }

    public void deleteEmployerById(Long id) {
        final Optional<Employer> employerOpt = employerRepository.findById(id);
        final Employer employer = employerOpt.orElseThrow(() -> new EmployerNotFoundException("id : " + id));
        employerRepository.delete(employer);
    }

    public Boolean checkUserGroupEmployer(Long employerId, UserGroup userGroup) {
        Optional<Employer> employerUpdateOpt = employerRepository.findById(employerId);
        if (employerUpdateOpt.isEmpty()) {
            throw new EmployerNotFoundException("id: " + employerId);
        }
        return employerUpdateOpt.get().getUserGroup().equals(userGroup);
    }
}
