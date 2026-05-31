package com.portfolio.payroll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StatusUpdateRequest {

    @NotBlank(message = "Status is required")
    @Pattern(regexp = "ACTIVE|INACTIVE|ON_LEAVE",
             message = "Status must be ACTIVE, INACTIVE or ON_LEAVE")
    private String status;
}
