package com.devlife.pf_sql_controller.service;

import com.devlife.pf_sql_controller.entity.Employer;
import com.devlife.pf_sql_controller.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerService {
    private final EmployerRepository employerRepository;

    public Long addEmployer(Employer employer) {
        Employer saveEmployer = employerRepository.save(employer);
        if (saveEmployer != null) {
            return saveEmployer.getId();
        }
        return null;
    }

    public Employer getEmployer(Long id) {
        Employer employer = employerRepository.getById(id);
        return employer;
    }

    public List<Employer> getAllEmployers() {
        List<Employer> employersList = employerRepository.findAll();
        return employersList;
    }

    public Boolean deleteEmployerById(Long id) {
        employerRepository.deleteById(id);
        return !employerRepository.existsById(id);
    }

}
