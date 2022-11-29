package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.dto.EmployerDto;
import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.entity.UserGroup;
import com.devlife.pf_sql_controller.exception.EmployerNotFoundException;
import com.devlife.pf_sql_controller.mapper.EmployerMapper;
import com.devlife.pf_sql_controller.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;
    private final EmployerMapper mapper;

    public Long addEmployer(EmployerDto employer) {
        Employer saveEmployer = employerRepository.save(mapper.convertToEntity(employer));
        if (saveEmployer != null) {
            return saveEmployer.getId();
        }
        return null;
    }

    public EmployerDto getEmployer(Long id) {
        Employer employer = employerRepository.getById(id);
        return mapper.convertToDto(employer);
    }

    public List<EmployerDto> getAllEmployers() {
        List<Employer> employersList = employerRepository.findAll();
        return employersList.stream().map(mapper::convertToDto).collect(Collectors.toList());
    }

    public Boolean deleteEmployerById(Long id) {
        employerRepository.deleteById(id);
        return !employerRepository.existsById(id);
    }


    public Boolean checkUserGroupEmployer(Long employerId, UserGroup userGroup) {
        Optional<Employer> employerUpdateOpt = employerRepository.findById(employerId);
        if (employerUpdateOpt.isEmpty()) {
            throw new EmployerNotFoundException("id: " + employerId);
        }
        return employerUpdateOpt.get().getUserGroup().equals(userGroup);
    }
}
