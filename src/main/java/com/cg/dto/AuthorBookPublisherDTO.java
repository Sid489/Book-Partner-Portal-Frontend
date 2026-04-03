package com.cg.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBookPublisherDTO {
    private String authorId;
    private String authorFirstName;
    private String authorLastName;
    private List<BookPublisherDetail> books;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BookPublisherDetail {
        private String titleId;
        private String title;
        private String publisherId;
        private String publisherName;
    }
}
