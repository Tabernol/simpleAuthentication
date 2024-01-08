package com.krasnoposlkyi.simpleauthentication.service;

import com.krasnoposlkyi.simpleauthentication.dao.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {

    UserDetailsService userDetailsService ();

    User get(Long id);
}
