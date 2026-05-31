package com.portfolio.payroll.dto;

import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
public class MarketSurveyResponse {

    private Long id;
    private String surveyName;
    private String provider;
    private Integer surveyYear;
    private String classCode;
    private String jobFamilyName;
    private String careerLevelName;
    private BigDecimal p25;
    private BigDecimal p50;
    private BigDecimal p75;
    private BigDecimal p90;
}
