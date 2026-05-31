package com.portfolio.payroll.dto;

import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;

@Data
@Builder
public class JobResponse {

    private Long id;
    private String jobCode;
    private String title;
    private String status;
    private String departmentName;
    private String divisionName;
    private String careerLevelName;
    private String careerLevelCode;
    private String jobFamilyName;
    private String jobFamilyGroupName;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String currency;
}
