package com.cg.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeWithJobDTO {
    private String empId;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private Integer jobLevel;
    private LocalDateTime hireDate;
    private JobDTO job;
}