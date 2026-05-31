package com.portfolio.payroll.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class JobRequest {

    @NotBlank(message = "Job code is required")
    @Size(max = 30, message = "Job code must not exceed 30 characters")
    private String jobCode;

    @NotBlank(message = "Job title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    private String title;

    @NotNull(message = "Class ID is required")
    private Long classId;

    @NotNull(message = "Department ID is required")
    private Long departmentId;
}
