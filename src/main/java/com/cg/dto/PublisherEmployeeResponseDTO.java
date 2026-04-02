package com.cg.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublisherEmployeeResponseDTO {
    private String pubId;
    private String pubName;
    private List<EmployeeWithJobDTO> employees;
}