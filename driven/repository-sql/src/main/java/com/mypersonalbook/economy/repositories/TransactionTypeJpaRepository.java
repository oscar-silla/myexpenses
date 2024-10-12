package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.TransactionTypeMO;
import com.mypersonalbook.economy.projections.IdProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionTypeJpaRepository extends JpaRepository<TransactionTypeMO, String> {
     Optional<IdProjection> findIdById(String id);
}
