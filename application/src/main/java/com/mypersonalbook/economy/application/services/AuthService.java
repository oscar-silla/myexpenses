package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.exceptions.UnauthorizedException;
import com.mypersonalbook.economy.config.auth.JwtUtil;
import com.mypersonalbook.economy.domain.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;

  public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
  }

  public String login(User user) {
    Authentication authentication = this.authenticateUser(user.getEmail(), user.getPassword());
    UserDetailsAdapter userDetails = (UserDetailsAdapter) authentication.getPrincipal();
    this.ensureUserIsVerified(userDetails.getUser());
    return this.jwtUtil.generateToken(userDetails);
  }

  private Authentication authenticateUser(String email, String password) {
    try {
      return this.authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, password));
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException(e.getMessage());
    }
  }

  private void ensureUserIsVerified(User user) {
    if (!user.isVerified()) {
      throw new UnauthorizedException();
    }
  }

  public Long getUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication != null
        && authentication.getCredentials()
            != null) { // TODO: Obtain id from principal, not credentials
      return (long) authentication.getCredentials();
    }
    return null;
  }
}
