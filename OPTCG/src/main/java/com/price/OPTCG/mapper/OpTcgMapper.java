package com.price.OPTCG.mapper;

import com.price.OPTCG.dto.OpTcgDTO;
import com.price.OPTCG.model.OpTcgModel;
import org.springframework.stereotype.Component;

@Component
public class OpTcgMapper {

    public OpTcgModel map(OpTcgDTO opTcgDTO) {

        OpTcgModel opTcgModel = new OpTcgModel();
        opTcgModel.setId(opTcgDTO.getId());
        opTcgModel.setInventoryPrice(opTcgDTO.getInventoryPrice());
        opTcgModel.setMarketPrice(opTcgDTO.getMarketPrice());
        opTcgModel.setCardName(opTcgDTO.getCardName());
        opTcgModel.setSetName(opTcgDTO.getSetName());
        opTcgModel.setCardText(opTcgDTO.getCardText());
        opTcgModel.setSetId(opTcgDTO.getSetId());
        opTcgModel.setRarity(opTcgDTO.getRarity());
        opTcgModel.setCardSetId(opTcgDTO.getCardSetId());
        opTcgModel.setCardColor(opTcgDTO.getCardColor());
        opTcgModel.setCardType(opTcgDTO.getCardType());
        opTcgModel.setLife(opTcgDTO.getLife());
        opTcgModel.setCardCost(opTcgDTO.getCardCost());
        opTcgModel.setCardPower(opTcgDTO.getCardPower());
        opTcgModel.setSubTypes(opTcgDTO.getSubTypes());
        opTcgModel.setCounterAmount(opTcgDTO.getCounterAmount());
        opTcgModel.setAttribute(opTcgDTO.getAttribute());
        opTcgModel.setDateScraped(opTcgDTO.getDateScraped());
        opTcgModel.setCardImageId(opTcgDTO.getCardImageId());
        opTcgModel.setCardImage(opTcgDTO.getCardImage());

        return opTcgModel;
    }

    public OpTcgDTO map(OpTcgModel opTcgModel) {

        OpTcgDTO opTcgDTO = new OpTcgDTO();
        opTcgDTO.setId(opTcgModel.getId());
        opTcgDTO.setInventoryPrice(opTcgModel.getInventoryPrice());
        opTcgDTO.setMarketPrice(opTcgModel.getMarketPrice());
        opTcgDTO.setCardName(opTcgModel.getCardName());
        opTcgDTO.setSetName(opTcgModel.getSetName());
        opTcgDTO.setCardText(opTcgModel.getCardText());
        opTcgDTO.setSetId(opTcgModel.getSetId());
        opTcgDTO.setRarity(opTcgModel.getRarity());
        opTcgDTO.setCardSetId(opTcgModel.getCardSetId());
        opTcgDTO.setCardColor(opTcgModel.getCardColor());
        opTcgDTO.setCardType(opTcgModel.getCardType());
        opTcgDTO.setLife(opTcgModel.getLife());
        opTcgDTO.setCardCost(opTcgModel.getCardCost());
        opTcgDTO.setCardPower(opTcgModel.getCardPower());
        opTcgDTO.setSubTypes(opTcgModel.getSubTypes());
        opTcgDTO.setCounterAmount(opTcgModel.getCounterAmount());
        opTcgDTO.setAttribute(opTcgModel.getAttribute());
        opTcgDTO.setDateScraped(opTcgModel.getDateScraped());
        opTcgDTO.setCardImageId(opTcgModel.getCardImageId());
        opTcgDTO.setCardImage(opTcgModel.getCardImage());

        return opTcgDTO;
    }
}
