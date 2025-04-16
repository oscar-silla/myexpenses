package com.mypersonalbook.economy.services.auth;

import com.mypersonalbook.economy.application.exceptions.UnauthorizedException;
import com.mypersonalbook.economy.application.services.AuthService;
import com.mypersonalbook.economy.application.services.UserDetailsAdapter;
import com.mypersonalbook.economy.config.auth.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static com.mypersonalbook.economy.utils.test.TestConstants.TOKEN;
import static com.mypersonalbook.economy.utils.test.TestConstants.USER_ID;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.USER;
import static com.mypersonalbook.economy.utils.test.mocks.user.UserMock.USER_NOT_VERIFIED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {
  AuthService authService;

  @Mock private AuthenticationManager authenticationManager;

  @Mock private JwtUtil jwtUtil;

  @BeforeEach
  void setUp() {
    this.authService = new AuthService(this.authenticationManager, this.jwtUtil);
  }

  @Test
  @DisplayName("Should generate token when login")
  void shouldGenerateToken_WhenLogin() {
    UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(USER);
    Authentication authentication = mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn(userDetailsAdapter);
    when(this.authenticationManager.authenticate(any())).thenReturn(authentication);
    when(this.jwtUtil.generateToken(any())).thenReturn(TOKEN);
    final String RESULT = this.authService.login(USER);
    verify(this.jwtUtil).generateToken(any());
    assertEquals(TOKEN, RESULT);
  }

  @Test
  @DisplayName("Should throw unauthorized exception when login")
  void shouldThrowUnauthorizedException_WhenLogin() {
    UserDetailsAdapter userDetailsAdapter = new UserDetailsAdapter(USER_NOT_VERIFIED);
    Authentication authentication = mock(Authentication.class);
    when(authentication.getPrincipal()).thenReturn(userDetailsAdapter);
    when(this.authenticationManager.authenticate(any())).thenReturn(authentication);
    final Executable EXECUTABLE = () -> this.authService.login(USER);
    assertThrows(UnauthorizedException.class, EXECUTABLE);
  }

  @Test
  @DisplayName("Should return user id when get user id")
  void shouldReturnUserId_WhenGetUserId() {
    Authentication authentication = mock(Authentication.class);
    SecurityContext securityContext = mock(SecurityContext.class);
    SecurityContextHolder.setContext(securityContext);
    when(securityContext.getAuthentication()).thenReturn(authentication);
    when(authentication.getCredentials()).thenReturn(USER_ID);
    final Long RESULT = this.authService.getUserId();
    assertEquals(USER_ID, RESULT);
  }
}
