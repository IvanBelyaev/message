package com.example.message.service;

import com.example.message.dto.LoginDTO;
import com.example.message.model.User;
import com.example.message.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return user;
    }

    @Transactional
    public void registerUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByName(loginDTO.getName());
        if (user.isPresent()) {
            throw new IllegalArgumentException("User with name " + loginDTO.getName() + " already exists");
        }
        userRepository.save(new User(loginDTO.getName(), "{noop}" + loginDTO.getPassword()));
    }
}
