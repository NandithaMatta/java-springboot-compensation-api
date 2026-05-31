package com.portfolio.payroll.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotBlank(message = "Employee number is required")
    @Pattern(regexp = "EMP\\d{3,}", message = "Employee number must start with EMP followed by digits e.g. EMP001")
    private String employeeNumber;

    @NotBlank(message = "First name is required")
    @Size(max = 100, message = "First name must not exceed 100 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 100, message = "Last name must not exceed 100 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Job ID is required")
    private Long jobId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;

    @NotNull(message = "Basic salary is required")
    @DecimalMin(value = "0.01", message = "Basic salary must be greater than 0")
    private BigDecimal basicSalary;

    @NotNull(message = "Hire date is required")
    private LocalDate hireDate;
}
