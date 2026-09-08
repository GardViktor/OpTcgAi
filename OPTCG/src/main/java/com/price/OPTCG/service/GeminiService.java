package com.price.OPTCG.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.price.OPTCG.model.OpTcgModel;
import com.price.OPTCG.repository.OpTcgRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class GeminiService {

    private final WebClient webClient;
    private final String geminiApiKey;
    private final OpTcgRepository opTcgRepository;

    public GeminiService(WebClient.Builder webClientBuilder,
                         @Value("${gemini.api.url}") String geminiApiUrl,
                         @Value("${gemini.api.key}") String geminiApiKey,
                         OpTcgRepository opTcgRepository) {
        this.webClient = webClientBuilder.baseUrl(geminiApiUrl).build();
        this.geminiApiKey = geminiApiKey;
        this.opTcgRepository = opTcgRepository;
    }

    public Mono<String> gerarAnalise() {
        List<OpTcgModel> cartas = opTcgRepository.findAll();
        String cartasJson = converterParaJson(cartas);

        String prompt = """
            Você é um analista especializado em One Piece TCG, com profundo conhecimento de sinergias entre cores, arquétipos de deck (Líder, Personagens, Eventos, Estágios) e meta competitivo do jogo.

            Você receberá uma lista de cartas em formato JSON, com os seguintes campos: card_name, set_name, card_text, rarity, card_set_id, card_color, card_type, life, card_cost, card_power, sub_types, counter_amount, attribute, inventory_price, market_price.

            Com base nessa lista, realize as seguintes análises, sempre no formato de tópicos:

            1. **Avaliação individual**: para cada carta, classifique como "Forte", "Situacional" ou "Fraca", com uma justificativa breve (1-2 frases) baseada em card_cost, card_power, counter_amount e sinergia potencial com card_color e sub_types.

            2. **Sugestão de arquétipo**: com base nas cores (card_color) e sub_types presentes na lista, sugira 1 ou 2 arquétipos de deck viáveis, explicando por quê.

            3. **Cartas-chave**: aponte quais cartas da lista seriam prioridade para incluir em um deck competitivo.

            4. **Pontos fracos**: identifique lacunas na lista (ex: falta de remoção, poucos personagens de baixo custo, ausência de counters relevantes).

            5. **Custo-benefício**: com base em market_price, aponte quais cartas fortes têm o melhor custo-benefício e quais são "hype de preço".

            6. **Cartas para vender**: aponte quais cartas são boas candidatas para venda, priorizando cartas "Fracas"/"Situacionais" com market_price alto, ou duplicadas/fora do arquétipo sugerido.

            Seja direto e técnico. Evite explicações genéricas sobre "o que é" o jogo.

            Lista de cartas:
            %s
            """.formatted(cartasJson);

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        return webClient.post()
                .uri("/v1beta/models/gemini-3.5-flash:generateContent")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("x-goog-api-key", geminiApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    var candidates = (List<Map<String, Object>>) response.get("candidates");
                    if (candidates != null && !candidates.isEmpty()) {
                        Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
                        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                        if (parts != null && !parts.isEmpty()) {
                            return parts.get(0).get("text").toString();
                        }
                    }

                    return "Nenhuma Analise foi gerada";
                });
    }

    private String converterParaJson(List<OpTcgModel> cartas) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            return objectMapper.writeValueAsString(cartas);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter cartas para JSON", e);
        }
    }
}