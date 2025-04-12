package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.enums.Role;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.ports.driven.UserRepositoryPort;
import com.mypersonalbook.economy.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
  private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(8);
  private final UserRepositoryPort userRepository;

  public UserService(UserRepositoryPort userRepository) {
    this.userRepository = userRepository;
  }

  public void save(User user) {
    String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
    user.setPassword(encodedPassword);
    user.setRole(Role.USER);
    this.userRepository.save(user);
  }

  public Optional<User> findByEmail(String email) {
    if (email != null) {
      return this.userRepository.findByEmail(email);
    } else {
      return Optional.empty();
    }
  }

  public void activateUserByEmail(String email) {
    this.userRepository.activateUserByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return this.userRepository
        .findByEmail(email)
        .map(UserDetailsAdapter::new)
        .orElseThrow(NotFoundException::new);
  }
}
