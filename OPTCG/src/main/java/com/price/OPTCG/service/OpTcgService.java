package com.price.OPTCG.service;

import com.price.OPTCG.dto.OpTcgDTO;
import com.price.OPTCG.mapper.OpTcgMapper;
import com.price.OPTCG.model.OpTcgModel;
import com.price.OPTCG.repository.OpTcgRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OpTcgService {

    private final OpTcgRepository opTcgRepository;
    private final OpTcgMapper opTcgMapper;
    public OpTcgService(OpTcgRepository opTcgRepository, OpTcgMapper opTcgMapper) {
        this.opTcgRepository = opTcgRepository;
        this.opTcgMapper = opTcgMapper;
    }

    public OpTcgDTO createCard(OpTcgDTO opTcgDTO) {
        OpTcgModel opTcgModel = opTcgMapper.map(opTcgDTO);
        opTcgModel = opTcgRepository.save(opTcgModel);
        return opTcgMapper.map(opTcgModel);
    }

    public List<OpTcgDTO> listCard() {
        List<OpTcgModel> opTcgModel = opTcgRepository.findAll();
        return opTcgModel.stream()
                .map(opTcgMapper::map)
                .collect(Collectors.toList());
    }

    public OpTcgDTO listCardId(String cardSetId) {
        Optional<OpTcgModel> opTcgModel = opTcgRepository.findByCardSetId(cardSetId);
        return opTcgModel.map(opTcgMapper::map).orElse(null);
    }

    public OpTcgDTO updateCardId(String cardSetId, OpTcgDTO opTcgDTO) {
        Optional<OpTcgModel> opTcgModel = opTcgRepository.findByCardSetId(cardSetId);
        if (opTcgModel.isPresent()) {
            OpTcgModel opTcgModelUpdate = opTcgMapper.map(opTcgDTO);
            opTcgDTO.setCardSetId(cardSetId);
            OpTcgModel opTcgModelCreate = opTcgRepository.save(opTcgModelUpdate);
            return opTcgMapper.map(opTcgModelCreate);
        }

        return null;
    }

    @Transactional
    public void deleteCardId(String cardSetId) {
        opTcgRepository.deleteByCardSetId(cardSetId);
    }
}
