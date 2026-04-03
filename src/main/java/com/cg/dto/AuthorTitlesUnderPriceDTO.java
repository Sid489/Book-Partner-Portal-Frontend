package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorTitlesUnderPriceDTO {

    private String titleId;
    private String bookTitle;
    private String bookType;
    private Double price;
    private Integer royalty;
    private Integer ytdSales;

    private String authorId;
    private String authorFullName;
    private Byte authorOrder;

    private Integer royaltyPercent;
}
