package com.price.OPTCG.service;

import com.price.OPTCG.dto.OpTcgDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class ApiService {

    private final WebClient webClient;

    public ApiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://www.optcgapi.com").build();
    }

    public List<OpTcgDTO> getCard(String cardSetId) {
        OpTcgDTO[] response = webClient.get()
                .uri("/api/sets/card/{cardSetId}/", cardSetId)
                .retrieve()
                .bodyToMono(OpTcgDTO[].class)
                .block(); // .block() converte de reativo pra síncrono

        return response != null ? Arrays.asList(response) : List.of();
    }
}