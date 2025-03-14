package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.enums.Role;
import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.exceptions.NotFoundException;
import com.mypersonalbook.economy.application.ports.driven.UserRepositoryPort;
import com.mypersonalbook.economy.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException();
        }
        this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userRepository.findByEmail(email).map(UserDetailsAdapter::new).orElseThrow(NotFoundException::new);
    }
}
