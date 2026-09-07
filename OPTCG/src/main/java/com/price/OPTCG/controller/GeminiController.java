package com.price.OPTCG.controller;

import com.price.OPTCG.service.GeminiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GeminiController {

    private final GeminiService geminiService;

    public GeminiController(GeminiService geminiService) {
        this.geminiService = geminiService;
    }

    @GetMapping
    public Mono<ResponseEntity<String>> gerarAnalise() {
        return geminiService.gerarAnalise();

    }
}
