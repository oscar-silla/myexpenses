package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.ExpenseMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseJpaRepository
    extends JpaRepository<ExpenseMO, Long>, JpaSpecificationExecutor<ExpenseMO> {}
