package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.config.auth.JwtUtil;
import com.mypersonalbook.economy.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  public String login(User user) {
    Authentication authentication =
        this.authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    return this.jwtUtil.generateToken((UserDetails) authentication.getPrincipal());
  }
}
