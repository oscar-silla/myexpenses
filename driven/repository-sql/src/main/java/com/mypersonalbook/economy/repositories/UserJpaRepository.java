package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.UserMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserMO, Long> {}
