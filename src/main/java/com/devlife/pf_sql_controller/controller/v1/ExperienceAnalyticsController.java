package com.devlife.pf_sql_controller.controller.v1;

import com.devlife.pf_sql_controller.dto.analyticFormDto.request.ExpFormRequest;
import com.devlife.pf_sql_controller.dto.analyticFormDto.response.ExpFormResponse;
import com.devlife.pf_sql_controller.service.analytic.ExperienceAnalyticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/analytics")
public class ExperienceAnalyticsController {
    private final ExperienceAnalyticService experienceAnalyticService;

    @PostMapping("getExp")
    public ExpFormResponse getExp(@RequestBody ExpFormRequest expForm) {
        return experienceAnalyticService.getExp(expForm);
    }
}
