package com.price.OPTCG.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.price.OPTCG.dto.OpTcgDTO;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ApiService {

    public List<OpTcgDTO> getCard(String cardSetId) {
        List<OpTcgDTO> cards = List.of();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.optcgapi.com/api/sets/card/" + cardSetId + "/"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());

            cards = mapper.readValue(response.body(), mapper.getTypeFactory()
                    .constructCollectionType(List.class, OpTcgDTO.class));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }
}