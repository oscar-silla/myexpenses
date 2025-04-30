package com.mypersonalbook.economy.config.auth;

import com.mypersonalbook.economy.application.exceptions.UnauthorizedException;
import com.mypersonalbook.economy.config.auth.exceptions.JwtAuthException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  @Value("${SECRET_KEY}")
  private String SECRET_KEY;

  private static final long EXPIRATION_TIME = 86400000; // 1 día

  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
        .compact();
  }

  public String extractEmail(String token) {
    try {
      return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    } catch (ExpiredJwtException e) {
      throw new JwtAuthException("Expired token");
    }
  }

  public boolean validateToken(String token, UserDetails userDetails) {
    return userDetails.getUsername().equals(extractEmail(token)) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return Jwts.parser()
        .setSigningKey(SECRET_KEY)
        .parseClaimsJws(token)
        .getBody()
        .getExpiration()
        .before(new Date());
  }
}
