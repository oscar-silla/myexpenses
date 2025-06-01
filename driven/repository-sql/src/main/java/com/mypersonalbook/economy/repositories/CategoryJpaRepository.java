package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.CategoryMO;
import com.mypersonalbook.economy.projections.CategoryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository
    extends JpaRepository<CategoryMO, Long>, JpaSpecificationExecutor<CategoryMO> {
  @Query(
      """
          SELECT
          c.name AS name,
          c.color AS color
          FROM CategoryMO c
            WHERE c.user.id = :userId
          GROUP BY c.name, c.color
          """)
  Page<CategoryProjection> findAllByUserId(Long userId, Pageable pageable);
}
