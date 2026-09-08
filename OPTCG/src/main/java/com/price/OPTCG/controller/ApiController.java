package com.price.OPTCG.controller;

import com.price.OPTCG.dto.OpTcgDTO;
import com.price.OPTCG.service.ApiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cards/api")
public class ApiController {

    private ApiService apiService;
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/list/{cardSetId}")
    public ResponseEntity<?> getCard(@PathVariable String cardSetId) {
        List<OpTcgDTO> cardApi = apiService.getCard(cardSetId);
        if (!cardApi.isEmpty()) {
            return ResponseEntity.ok(cardApi);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Card [" + cardSetId + "] Not Found");
        }
    }
}
