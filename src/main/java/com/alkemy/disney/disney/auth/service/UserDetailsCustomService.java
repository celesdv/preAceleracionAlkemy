package com.alkemy.disney.disney.auth.service;

import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.UserEntity;
import com.alkemy.disney.disney.auth.repository.UserRepository;
import com.alkemy.disney.disney.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);
        if(username == null) {
            throw new UsernameNotFoundException("Username or password not found.");
        }
        return new User(user.getUsername(), user.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user = this.userRepository.save(user);
        if(user != null) {
        emailService.sendWelcomeEmailTo(user.getUsername());
        }
        return user != null;

    }
}
