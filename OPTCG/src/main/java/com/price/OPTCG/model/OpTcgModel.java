package com.price.OPTCG.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tb_op_cards")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpTcgModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal inventoryPrice;
    private BigDecimal marketPrice;
    private String cardName;
    private String setName;
    private String cardText;
    private String setId;
    private String rarity;
    @Column(nullable = true)
    private String cardSetId;
    private String cardColor;
    private String cardType;
    private Integer life;
    private String cardCost;
    private String cardPower;
    private String subTypes;
    private Integer counterAmount;
    private String attribute;
    private LocalDate dateScraped;
    private String cardImageId;
    private String cardImage;
}
