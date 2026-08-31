package com.price.OPTCG.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OpTcgDTO {

    private Long id;
    @JsonProperty("inventory_price")
    private BigDecimal inventoryPrice;
    @JsonProperty("market_price")
    private BigDecimal marketPrice;
    @JsonProperty("card_name")
    private String cardName;
    @JsonProperty("set_name")
    private String setName;
    @JsonProperty("card_text")
    private String cardText;
    @JsonProperty("set_id")
    private String setId;
    private String rarity;
    @JsonProperty("card_set_id")
    private String cardSetId;
    @JsonProperty("card_color")
    private String cardColor;
    @JsonProperty("card_type")
    private String cardType;
    private Integer life;
    @JsonProperty("card_cost")
    private String cardCost;
    @JsonProperty("card_power")
    private String cardPower;
    @JsonProperty("sub_types")
    private String subTypes;
    @JsonProperty("counter_amount")
    private Integer counterAmount;
    private String attribute;
    @JsonProperty("date_scraped")
    private LocalDate dateScraped;
    @JsonProperty("card_image_id")
    private String cardImageId;
    @JsonProperty("card_image")
    private String cardImage;
}
