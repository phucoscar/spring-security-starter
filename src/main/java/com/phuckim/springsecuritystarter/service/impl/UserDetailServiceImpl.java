package com.phuckim.springsecuritystarter.service.impl;

import com.phuckim.springsecuritystarter.model.entity.User;
import com.phuckim.springsecuritystarter.model.entity.UserPrinciple;
import com.phuckim.springsecuritystarter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Phuckim
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserPrinciple loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserPrinciple(
                username,
                user.getPassword(),
                getGrantedAuthority(user),
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    private List<GrantedAuthority> getGrantedAuthority(User user) {
        List<String> roles = Arrays.asList(user.getRole().split(","));

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
