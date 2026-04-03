package com.cg.service;

import com.cg.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.*;

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
                                    .map(error -> new RuntimeException(error.getMessage()))
                    )
                    .bodyToMono(PublisherEmployeeResponseDTO.class)
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
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
                                    .map(error -> new RuntimeException(error.getMessage()))
                    )
                    .bodyToFlux(BestSellingBookDTO.class)
                    .collectList()
                    .block();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}