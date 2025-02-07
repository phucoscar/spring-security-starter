package com.phuckim.springsecuritystarter.controller;

import com.phuckim.springsecuritystarter.helper.AuthenticationHelper;
import com.phuckim.springsecuritystarter.service.UserService;
import com.phuckim.springsecuritystarter.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HIVELAB
 */
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> me() {
        return ResponseEntity.ok(AuthenticationHelper.getCurrentUsername());
    }
}
