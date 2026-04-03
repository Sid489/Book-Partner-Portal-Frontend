package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiAuthorTitlesDTO {

    private String titleId;
    private String title;
    private String type;
    private Long authorCount;
    private List<String> authorNames;
}