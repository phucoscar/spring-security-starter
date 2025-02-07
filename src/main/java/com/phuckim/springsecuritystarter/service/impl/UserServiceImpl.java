package com.phuckim.springsecuritystarter.service.impl;

import com.phuckim.springsecuritystarter.model.entity.User;
import com.phuckim.springsecuritystarter.repository.UserRepository;
import com.phuckim.springsecuritystarter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author HIVELAB
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }
}
