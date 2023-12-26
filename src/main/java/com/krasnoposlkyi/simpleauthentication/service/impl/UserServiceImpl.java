package com.krasnoposlkyi.simpleauthentication.service.impl;

import com.krasnoposlkyi.simpleauthentication.dto.UserPostDto;
import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import com.krasnoposlkyi.simpleauthentication.dao.repository.UserRepository;
import com.krasnoposlkyi.simpleauthentication.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

//    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository
//                           ,UserMapper userMapper
    ) {
        this.userRepository = userRepository;
//        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public User add(UserPostDto userPostDto) {
//        User user = userMapper.map(userPostDto);
//        return userRepository.save(user);
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(()
                        -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByUsername(username)
                        .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
            }
        };
    }


}
