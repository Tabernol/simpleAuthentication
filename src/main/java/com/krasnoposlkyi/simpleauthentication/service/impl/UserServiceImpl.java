package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.krasnoposlkyi.simpleauthentication.dto.UserPostDto;
import com.krasnoposlkyi.simpleauthentication.entity.User;
import com.krasnoposlkyi.simpleauthentication.repository.UserRepository;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public User add(UserPostDto userPostDto) {
        return userRepository.save(new User(userPostDto.getUsername(), userPostDto.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
