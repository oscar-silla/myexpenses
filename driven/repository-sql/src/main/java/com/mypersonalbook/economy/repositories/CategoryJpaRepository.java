package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.CategoryMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaRepository
    extends JpaRepository<CategoryMO, Long>, JpaSpecificationExecutor<CategoryMO> {}
