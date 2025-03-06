package com.mypersonalbook.economy.application.services;

import com.mypersonalbook.economy.application.exceptions.ConflictException;
import com.mypersonalbook.economy.application.ports.driven.UserRepositoryPort;
import com.mypersonalbook.economy.domain.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
    private final UserRepositoryPort userRepository;

    public UserService(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if (this.userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ConflictException();
        }
        this.userRepository.save(user);
    }
}
