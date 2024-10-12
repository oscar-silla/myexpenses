package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.TransactionMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionJpaRepository
    extends JpaRepository<TransactionMO, Long>, JpaSpecificationExecutor<TransactionMO> {}
