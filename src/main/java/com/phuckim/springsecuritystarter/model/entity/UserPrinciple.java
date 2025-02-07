package com.phuckim.springsecuritystarter.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author HIVELAB
 */
@Getter
@Setter
public class UserPrinciple extends User {

    private int id;

    private String name;

    private String email;

    private String role;

    public UserPrinciple(String username, String password, Collection<? extends GrantedAuthority> authorities, int id, String name, String email, String role) {
        super(username, password, authorities);
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }
}
