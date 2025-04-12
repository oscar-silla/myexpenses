package com.mypersonalbook.economy.repositories;

import com.mypersonalbook.economy.models.UserMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserMO, Long> {
  Optional<UserMO> findByEmail(String email);

  @Query(
      value =
          """
    UPDATE UserMO u
    SET u.isVerified = true
    WHERE u.email = :email
  """)
  @Modifying
  @Transactional
  void activateUserByEmail(@Param("email") String email);
}
