package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.TransactionMO;
import com.mypersonalbook.economy.projections.TransactionsSummaryProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface TransactionJpaRepository
    extends JpaRepository<TransactionMO, Long>, JpaSpecificationExecutor<TransactionMO> {
  @Query(
      """
        SELECT
            SUM(CASE WHEN transaction.type.id = 'REVENUE' THEN transaction.amount ELSE 0 END) AS totalRevenue,
            SUM(CASE WHEN transaction.type.id = 'EXPENSE' THEN transaction.amount ELSE 0 END) AS totalExpense,
            SUM(CASE
                WHEN transaction.type.id = 'REVENUE' THEN transaction.amount
                WHEN transaction.type.id = 'EXPENSE' THEN - transaction.amount
                ELSE 0 END) AS totalMoney
        FROM TransactionMO transaction
        WHERE transaction.date BETWEEN :startDate AND :endDate
          AND transaction.user.id = :userId
          AND transaction.type.id IN ('REVENUE', 'EXPENSE')
    """)
  TransactionsSummaryProjection getTransactionsSummary(
      @Param("userId") Long userId,
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}
