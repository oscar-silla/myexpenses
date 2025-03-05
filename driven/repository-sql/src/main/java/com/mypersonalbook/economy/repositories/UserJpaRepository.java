package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.UserMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserMO, Long> {
    Optional<UserMO> findByEmail(String email);
}
