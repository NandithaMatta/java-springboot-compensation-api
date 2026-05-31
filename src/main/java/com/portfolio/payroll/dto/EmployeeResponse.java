package com.portfolio.payroll.dto;

import lombok.Data;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeResponse {

    private Long id;
    private String employeeNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String jobCode;
    private String departmentName;
    private String divisionName;
    private String organizationName;
    private String careerLevel;
    private String jobFamily;
    private BigDecimal basicSalary;
    private BigDecimal minSalaryBand;
    private BigDecimal maxSalaryBand;
    private LocalDate hireDate;
    private String status;
    private LocalDateTime createdAt;
}
