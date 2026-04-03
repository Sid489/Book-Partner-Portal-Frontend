package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BestSellingBookDTO {

    private String authorId;
    private String authorName;
    private String titleId;
    private String titleName;
    private Long totalSales;
    private Double revenue;
}