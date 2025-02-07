package com.phuckim.springsecuritystarter.controller;

import com.phuckim.springsecuritystarter.model.dto.LoginRequestDTO;
import com.phuckim.springsecuritystarter.model.dto.LoginResponseDTO;
import com.phuckim.springsecuritystarter.model.entity.User;
import com.phuckim.springsecuritystarter.model.entity.UserPrinciple;
import com.phuckim.springsecuritystarter.service.impl.UserDetailServiceImpl;
import com.phuckim.springsecuritystarter.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Phuckim
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailServiceImpl userDetailsService;

    @PostMapping(value = "/login", produces = "application/json")
    public Object doLogin(@RequestBody LoginRequestDTO request) {
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserPrinciple userPrinciple = userDetailsService.loadUserByUsername(request.getUsername());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = JwtUtil.generateToken(User.builder()
                            .id(userPrinciple.getId())
                            .name(userPrinciple.getName())
                            .username(userPrinciple.getUsername())
                            .email(userPrinciple.getEmail())
                            .role(userPrinciple.getRole())
                            .build()
            );

            return ResponseEntity.ok(LoginResponseDTO.builder().accessToken(accessToken).build());
        } catch (AuthenticationException e) {
            log.info("Authentication failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username or password is incorrect.");
        }
    }
}