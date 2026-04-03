package com.cg.service;

import com.cg.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;
import org.springframework.core.ParameterizedTypeReference;
import java.util.List;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient webClient) {
        this.webClient = webClient;
    }

    public PublisherEmployeeResponseDTO getEmployees(String pubId) {

        try {
            return webClient.get()
                    .uri("/api/publishers/" + pubId + "/employees-with-jobs")
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .map(error -> new RuntimeException(error.getMessage())))
                    .bodyToMono(PublisherEmployeeResponseDTO.class)
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<AuthorBookPublisherDTO> getAuthorsWithBooksAndPublishers() {
        try {
            return webClient.get()
                    .uri("/api/authors/books-with-publishers")
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .map(error -> new RuntimeException(error.getMessage())))
                    .bodyToMono(new ParameterizedTypeReference<List<AuthorBookPublisherDTO>>() {
                    })
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Authors data: " + e.getMessage());
        }
    }

    public List<BestSellingBookDTO> getBestSellingBooks() {

        try {
            return webClient.get()
                    .uri("/api/authors/best-selling-books")
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .map(error -> new RuntimeException(error.getMessage())))

                    .bodyToFlux(BestSellingBookDTO.class)
                    .collectList()
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<AuthorTitlesUnderPriceDTO> getTitlesUnderPrice(Double maxPrice) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/api/titles/filter")
                            .queryParam("maxPrice", maxPrice)
                            .build())
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .map(error -> new RuntimeException(error.getMessage()))
                    )
                    .bodyToFlux(AuthorTitlesUnderPriceDTO.class)
                    .collectList()
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    public List<MultiAuthorTitlesDTO> getMultiAuthorBooks() {

        try {
            return webClient.get()
                    .uri("/api/titles/multi-author-titles") // backend API 9
                    .retrieve()
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(ErrorResponse.class)
                                    .map(error -> new RuntimeException(error.getMessage()))
                    )
                    .bodyToMono(new ParameterizedTypeReference<List<MultiAuthorTitlesDTO>>() {})
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}