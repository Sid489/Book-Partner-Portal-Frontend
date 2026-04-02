package com.cg.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobDTO {
    private Short jobId;
    private String jobDesc;
    private Integer minLvl;
    private Integer maxLvl;
}