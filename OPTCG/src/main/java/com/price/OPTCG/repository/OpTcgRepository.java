package com.price.OPTCG.repository;

import com.price.OPTCG.model.OpTcgModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OpTcgRepository extends JpaRepository<OpTcgModel, Long> {
    Optional<OpTcgModel> findByCardSetId(String cardSetId);
    void deleteByCardSetId(String cardSetId);;
}
