package com.devlife.pf_sql_controller.service.analytic;

import com.devlife.pf_sql_controller.dto.analyticFormDto.bdModel.ExpFormBd;
import com.devlife.pf_sql_controller.dto.analyticFormDto.request.ExpFormRequest;
import com.devlife.pf_sql_controller.dto.analyticFormDto.response.ExpFormResponse;
import com.devlife.pf_sql_controller.repository.ProjectRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ExperienceAnalyticService {
    private final ProjectRoleRepository projectRoleRepository;

    public ExpFormResponse getExp(ExpFormRequest expFormRequest) {
        Long userId = expFormRequest.getUserId();
        if (userId == null){
            return null;
        }
        if (expFormRequest.getRoleId() != null){
            List<ExpFormBd> userExp = projectRoleRepository.getUserExpByRole(userId, expFormRequest.getRoleId());
            return calculateExpFormResponse(userExp);
        }
        else if (expFormRequest.getProjectId() != null){
            List<ExpFormBd> userExp = projectRoleRepository.getUserExpByProject(userId, expFormRequest.getProjectId());
            return calculateExpFormResponse(userExp);
        }
        else if (expFormRequest.getEmployerId() != null){
            List<ExpFormBd> userExp = projectRoleRepository.getUserExpByEmployer(userId, expFormRequest.getEmployerId());
            return calculateExpFormResponse(userExp);
        }
        List<ExpFormBd> userExp = projectRoleRepository.getUserAllExp(userId);
        return calculateExpFormResponse(userExp);
    }

    private ExpFormResponse calculateExpFormResponse(List<ExpFormBd> userAllExp) {
        Period expPeriod = userAllExp.stream()
                .reduce(Period.ofDays(0),
                        (x, y) -> x.plus(Period.between(y.getStartDate(), y.getEndDate())),
                        Period::plus).normalized();
        return new ExpFormResponse(expPeriod.getDays(), expPeriod.getMonths(), expPeriod.getYears());
    }
}
