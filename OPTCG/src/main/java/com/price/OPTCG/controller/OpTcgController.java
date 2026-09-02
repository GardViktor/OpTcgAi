package com.price.OPTCG.controller;

import com.price.OPTCG.dto.OpTcgDTO;
import com.price.OPTCG.service.ApiService;
import com.price.OPTCG.service.OpTcgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
public class OpTcgController {

    private final OpTcgService opTcgService;
    private final ApiService apiService;
    public OpTcgController(OpTcgService opTcgService, ApiService apiService) {
        this.opTcgService = opTcgService;
        this.apiService = apiService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCard(@RequestBody OpTcgDTO opTcgDTO) {
        OpTcgDTO opTcgCreate = opTcgService.createCard(opTcgDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Card [" + opTcgCreate.getCardSetId() + "] Create");
    }

    @GetMapping("/list")
    public ResponseEntity<List<OpTcgDTO>> listCard() {
        List<OpTcgDTO> OpTcgRead = opTcgService.listCard();
        return ResponseEntity.ok(OpTcgRead);
    }

    @GetMapping("/list/{cardSetId}")
    public ResponseEntity<?> listCardId(@PathVariable String cardSetId) {
        OpTcgDTO cardLocal = opTcgService.listCardId(cardSetId);
        if (cardLocal != null) {
            return ResponseEntity.ok(cardLocal);
        }

        List<OpTcgDTO> cardsApi = apiService.getCard(cardSetId);
        if (cardsApi != null && !cardsApi.isEmpty()) {
            OpTcgDTO cardSalvo = opTcgService.createCard(cardsApi.get(0));
            return ResponseEntity.ok(cardSalvo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Card [" + cardSetId + "] Not Found");
    }

    @PutMapping("/update/{cardSetId}")
    public ResponseEntity<?> updateCardId(@PathVariable String cardSetId, @RequestBody OpTcgDTO opTcgDTO) {
        OpTcgDTO opTcgUpdate = opTcgService.updateCardId(cardSetId, opTcgDTO);
        if (opTcgUpdate != null) {
            return ResponseEntity.ok(opTcgUpdate);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Card [" + cardSetId + "] Not Found");
        }
    }

    @DeleteMapping("/delete/{cardSetId}")
    public  ResponseEntity<String> deleteCardId(@PathVariable String cardSetId) {
        if (opTcgService.listCardId(cardSetId) != null) {
            opTcgService.deleteCardId(cardSetId);
            return ResponseEntity.ok("Card [" + cardSetId + "] Delete");
        } else { return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Card [" + cardSetId + "] Not Found");
        }
    }

}
