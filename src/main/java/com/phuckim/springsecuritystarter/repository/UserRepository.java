package com.phuckim.springsecuritystarter.repository;

import com.phuckim.springsecuritystarter.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Phuckim
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
