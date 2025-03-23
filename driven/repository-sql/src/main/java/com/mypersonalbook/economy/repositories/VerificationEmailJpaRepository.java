package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.VerificationEmailMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationEmailJpaRepository
    extends JpaRepository<VerificationEmailMO, String> {}
